package example.service;

import example.constant.ElasticSearchConstant;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DeleteData {

    @Autowired
    private RestHighLevelClient client;

    private final List<String> jobTypeList = jobTypeList();

    public void deleteByBoolQuery(String groupName, String jobType, String pkSeq) {
        try {
            BoolQueryBuilder bq = QueryBuilders.boolQuery()
                                               .must(new TermQueryBuilder("groupName", groupName))
                                               .must(new TermQueryBuilder("jobType", jobType))
                                               .must(new TermQueryBuilder("pkSeq", pkSeq));

            DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(ElasticSearchConstant.FILE_INDEX_NAME);
            deleteByQueryRequest.setQuery(bq);

            client.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            System.out.println("bool 쿼리를 사용하여 도큐먼트 삭제 시 예외 발생, msg = " + e.getMessage());
        }
    }

    public void deleteContentWithFile(String groupName, String jobType, String pkSeq) {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            // delete content
            String contentDocId = getContentDocId(groupName, jobType, pkSeq);
            bulkRequest.add(new DeleteRequest(ElasticSearchConstant.CONTENT_INDEX_NAME).id(contentDocId));

            // delete file
            List<String> linkedFilesId = getLinkedFilesId(groupName, jobType, pkSeq);
            for (String fileId : linkedFilesId) {
                bulkRequest.add(new DeleteRequest(ElasticSearchConstant.FILE_INDEX_NAME).id(fileId));
                DeleteRequest deleteRequest = new DeleteRequest(ElasticSearchConstant.FILE_INDEX_NAME);
            }
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            System.out.println("본문과 첨부 파일을 함께 삭제 시 예외 발생, mgs = " + e.getMessage());
        }
    }

    public void deleteFilesWithoutContent(String groupName) throws IOException {
        // 본문만 이미 삭제되어 첨부파일만 남아있는 경우를 찾아서 삭제
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchReqForFile = new SearchRequest(ElasticSearchConstant.FILE_INDEX_NAME);
        SearchRequest searchReqForContent = new SearchRequest(ElasticSearchConstant.CONTENT_INDEX_NAME);
        for (String jobType : jobTypeList) {
            BoolQueryBuilder bqForGetPkSeq = QueryBuilders.boolQuery()
                                                          .must(new TermQueryBuilder("groupName", groupName))
                                                          .must(new TermQueryBuilder("jobType", jobType));

            searchSourceBuilder.query(bqForGetPkSeq);
            searchReqForFile.source(searchSourceBuilder);
            SearchResponse resForPkSeq = client.search(searchReqForFile, RequestOptions.DEFAULT);
            for (SearchHit hit : resForPkSeq.getHits().getHits()) {
                String pkSeq = hit.getSourceAsMap().get("pkSeq").toString();
                BoolQueryBuilder bqForGetFileWithoutContent = QueryBuilders.boolQuery()
                                                                           .must(new TermQueryBuilder("groupName", groupName))
                                                                           .must(new TermQueryBuilder("jobType", jobType))
                                                                           .must(new TermQueryBuilder("pkSeq", pkSeq));
                searchSourceBuilder.query(bqForGetFileWithoutContent);
                searchReqForContent.source(searchSourceBuilder);
                SearchResponse resForGetFileWithoutContent = client.search(searchReqForContent, RequestOptions.DEFAULT);

                if (resForGetFileWithoutContent.getHits().getTotalHits().value == 0L) {
                    client.deleteByQuery(new DeleteByQueryRequest(ElasticSearchConstant.FILE_INDEX_NAME).setQuery(bqForGetFileWithoutContent), RequestOptions.DEFAULT);
                }

            }
        }

    }

    public List<String> jobTypeList() {
        List<String> jobTypeList = new ArrayList<>();
        jobTypeList.add("board");
        jobTypeList.add("note");
        jobTypeList.add("schedule");
        return jobTypeList;
    }


    public String getContentDocId(String groupName, String jobType, String pkSeq) {
        String contentDocId = null;
        try {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(new TermQueryBuilder("groupName", groupName));
            boolQueryBuilder.must(new TermQueryBuilder("jobType", jobType));
            boolQueryBuilder.must(new TermQueryBuilder("pkSeq", pkSeq));

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(boolQueryBuilder);

            SearchRequest searchRequest = new SearchRequest(ElasticSearchConstant.CONTENT_INDEX_NAME);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            if (searchResponse.getHits().getTotalHits().value == 0L) {
                System.out.println("본문이 없음");
            }

            for (SearchHit hit : searchResponse.getHits().getHits()) {
                contentDocId = hit.getId();
            }
        } catch (Exception e) {
            System.out.println("본문 검색 시 예외 발생, mgs = " + e.getMessage());
        }
        return contentDocId;
    }

    public List<String> getLinkedFilesId(String groupName, String jobType, String pkSeq) {
        List<String> linkedFilesId = new ArrayList<>();
        try {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(new TermQueryBuilder("groupName", groupName));
            boolQueryBuilder.must(new TermQueryBuilder("jobType", jobType));
            boolQueryBuilder.must(new TermQueryBuilder("pkSeq", pkSeq));

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(boolQueryBuilder);

            SearchRequest searchRequest = new SearchRequest(ElasticSearchConstant.FILE_INDEX_NAME);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            if (searchResponse.getHits().getTotalHits().value == 0L) {
                System.out.println("첨부파일 없음");
                return new ArrayList<>();
            }

            for (SearchHit linkedFile : searchResponse.getHits().getHits()) {
                linkedFilesId.add(linkedFile.getId());
            }
        } catch (Exception e) {
            System.out.println("파일 검색 시 예외 발생, msg = " + e.getMessage());
        }
        return linkedFilesId;
    }

    public void deleteFilesWithoutInfo1(List<String> groupNames) throws IOException {

        RequestOptions.Builder options = RequestOptions.DEFAULT.toBuilder();
        options.setHttpAsyncResponseConsumerFactory(new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(2 * 104857600));
        int MAX_SIZE = 1000;

        for (String groupName : groupNames) {
            List<String> fileIds = new ArrayList<>();

            String contentIndexName = "content";
            String fileIndexName = "file";

            SearchRequest contentSearchReq = new SearchRequest(contentIndexName);
            SearchRequest fileSearchReq = new SearchRequest(fileIndexName);

            Object[] searchAfterValues = null; // 첫 번째 페이지는 null로 설정

            for (String jobType : jobTypeList) {
                while (true) {
                    SearchSourceBuilder searchSource1 = new SearchSourceBuilder();

                    if (searchAfterValues != null) {
                        searchSource1.searchAfter(searchAfterValues);
                    }

                    BoolQueryBuilder fileBq = QueryBuilders.boolQuery()
                                                           .must(QueryBuilders.termQuery("groupName", groupName))
                                                           .must(QueryBuilders.termQuery("jobType", jobType));

                    searchSource1.query(fileBq)
                                 .size(MAX_SIZE)
                                 .sort("_id", SortOrder.ASC)
                                 .fetchSource("pkSeq", null);

                    fileSearchReq.source(searchSource1);

                    SearchResponse fileSearchRes = client.search(fileSearchReq, options.build());

                    SearchHit[] searchFileHits = fileSearchRes.getHits().getHits();

                    for (SearchHit file : searchFileHits) {
                        String filePkSeq = file.getSourceAsMap().get("pkSeq").toString();
                        BoolQueryBuilder contentBq = QueryBuilders.boolQuery()
                                                                  .must(QueryBuilders.termQuery("groupName", groupName))
                                                                  .must(QueryBuilders.termQuery("jobType", jobType))
                                                                  .must(QueryBuilders.termQuery("pkSeq", filePkSeq));

                        SearchSourceBuilder searchSource2 = new SearchSourceBuilder();
                        searchSource2.query(contentBq)
                                     .size(MAX_SIZE)
                                     .fetchSource("_id", null);
                        contentSearchReq.source(searchSource2);

                        SearchResponse contentSearchRes = client.search(contentSearchReq, options.build());

                        if (contentSearchRes.getHits().getTotalHits().value == 0L) {
                            fileIds.add(file.getId());
                        }
                    }

                    if (searchFileHits.length > 0) { // 다음 페이지 검색을 위해 마지막 문서 정보 저장
                        SearchHit lastHit = searchFileHits[searchFileHits.length - 1];
                        searchAfterValues = lastHit.getSortValues();
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public void deleteFilesWithoutInfo2() throws IOException {

        RequestOptions.Builder options = RequestOptions.DEFAULT.toBuilder();
        options.setHttpAsyncResponseConsumerFactory(new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(2 * 104857600));
        int MAX_SIZE = 1000;


        String contentIndexName = "content";
        String fileIndexName = "file";

        SearchRequest contentSearchReq = new SearchRequest(contentIndexName);
        SearchRequest fileSearchReq = new SearchRequest(fileIndexName);

        Object[] searchAfterValues = null; // 첫 번째 페이지는 null로 설정

        for (String jobType : jobTypeList) {
            while (true) {
                List<String> fileIds = new ArrayList<>();
                SearchSourceBuilder searchSource1 = new SearchSourceBuilder();

                if (searchAfterValues != null) {
                    searchSource1.searchAfter(searchAfterValues);
                }

                BoolQueryBuilder fileBq = QueryBuilders.boolQuery()
                                                       .must(QueryBuilders.termQuery("jobType", jobType));

                searchSource1.query(fileBq)
                             .size(MAX_SIZE)
                             .sort("_id", SortOrder.ASC)
                             .fetchSource(new String[]{"groupName", "pkSeq"}, null);

                fileSearchReq.source(searchSource1);

                SearchResponse fileSearchRes = client.search(fileSearchReq, options.build());

                SearchHit[] searchFileHits = fileSearchRes.getHits().getHits();

                for (SearchHit file : searchFileHits) {
                    Map<String, Object> sourceMap = file.getSourceAsMap();
                    String getGroupName = sourceMap.get("groupName").toString();
                    String getPkSeq = sourceMap.get("pkSeq").toString();
                    BoolQueryBuilder contentBq = QueryBuilders.boolQuery()
                                                              .must(QueryBuilders.termQuery("groupName", getGroupName))
                                                              .must(QueryBuilders.termQuery("jobType", jobType))
                                                              .must(QueryBuilders.termQuery("pkSeq", getPkSeq));

                    SearchSourceBuilder searchSource2 = new SearchSourceBuilder();
                    searchSource2.query(contentBq)
                                 .fetchSource("_id", null);
                    contentSearchReq.source(searchSource2);

                    SearchResponse contentSearchRes = client.search(contentSearchReq, options.build());

                    if (contentSearchRes.getHits().getTotalHits().value == 0L) {
                        fileIds.add(file.getId());
                    }
                }

                if (searchFileHits.length > 0) { // 다음 페이지 검색을 위해 마지막 문서 정보 저장
                    SearchHit lastHit = searchFileHits[searchFileHits.length - 1];
                    searchAfterValues = lastHit.getSortValues();
                } else {
                    break;
                }
            }
        }
    }

    public void getFileIdWithoutInfo3() throws IOException {

        List<String> jobList = Arrays.asList("board", "note", "schedule");
        SearchRequest contentSearchReq = new SearchRequest("content");
        SearchRequest fileSearchReq = new SearchRequest("file");

        int MAX_SIZE = 1000;

        Object[] searchAfterValues = null;

        while (true) {
            List<String> fileIdForDel = new ArrayList<>();
            SearchSourceBuilder fileSearchSource = new SearchSourceBuilder();

            if (searchAfterValues != null) {
                fileSearchSource.searchAfter(searchAfterValues);
            }

            fileSearchReq.source(fileSearchSource.query(QueryBuilders.matchAllQuery())
                                                 .size(MAX_SIZE)
                                                 .sort("_id", SortOrder.ASC)
                                                 .fetchSource(new String[]{"groupName", "jobType", "pkSeq"}, null));

            SearchResponse fileSearchRes = client.search(fileSearchReq, RequestOptions.DEFAULT);

            SearchHit[] fileSearchHits = fileSearchRes.getHits().getHits();
            for (SearchHit fileSearchHit : fileSearchHits) {
                if (fileSearchHit.getSourceAsMap() == null
                        || ObjectUtils.isEmpty(fileSearchHit.getSourceAsMap().get("groupName"))
                        || ObjectUtils.isEmpty(fileSearchHit.getSourceAsMap().get("jobType"))
                        || ObjectUtils.isEmpty(fileSearchHit.getSourceAsMap().get("pkSeq"))) {
                    continue;
                }

                String groupName = fileSearchHit.getSourceAsMap().get("groupName").toString();
                String jobType = fileSearchHit.getSourceAsMap().get("jobType").toString();
                String pkSeq = fileSearchHit.getSourceAsMap().get("pkSeq").toString();


                if (!jobList.contains(jobType)) {
                    continue;
                }

                BoolQueryBuilder bq = new BoolQueryBuilder();
                bq.must(new TermQueryBuilder("groupName", groupName));
                bq.must(new TermQueryBuilder("jobType", jobType));
                bq.must(new TermQueryBuilder("pkSeq", pkSeq));

                contentSearchReq.source(new SearchSourceBuilder()
                        .query(bq)
                        .fetchSource("_id", null)
                        .explain(false));

                SearchResponse contentSearchRes = client.search(contentSearchReq, RequestOptions.DEFAULT);

                if (contentSearchRes.getHits().getTotalHits().value == 0L) {
                    fileIdForDel.add(fileSearchHit.getId());
                }

            }
            deleteBulk(fileIdForDel);

            if (fileSearchHits.length > 0) { // 다음 페이지 검색을 위해 마지막 문서 정보 저장
                SearchHit lastHit = fileSearchHits[fileSearchHits.length - 1];
                searchAfterValues = lastHit.getSortValues();
            } else {
                break;
            }
        }
    }

    public void deleteBulk(List<String> ids) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (String id : ids) {
            bulkRequest.add(new DeleteRequest("file").id(id));
        }

        if (bulkRequest.numberOfActions() > 0) {
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
        }
    }

}
