package com.huang.Stream;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author: I325805
 * @description:
 */
public class Util {
    public <T> T sendRequest(Class<T> responseType) {
        String body = "<n0:TransportationEventBulkNotification xmlns:n0=\"http://sap.com/xi/SAPGlobal/Global\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><MessageHeader><CreationDateTime>2020-11-19T09:26:26.175Z</CreationDateTime><RecipientBusinessSystemID>TO_QM7_910_ALEX_TM</RecipientBusinessSystemID><RecipientParty><StandardID schemeAgencyID=\"310\">10013165</StandardID></RecipientParty><SenderParty><InternalID>LBN-GTT-SO-01</InternalID><StandardID schemeAgencyID=\"310\">10000014</StandardID></SenderParty></MessageHeader><TransportationEventNotificationMessage><MessageHeader><CreationDateTime>2020-11-19T09:26:26.175Z</CreationDateTime><RecipientBusinessSystemID>TO_QM7_910_ALEX_TM</RecipientBusinessSystemID><RecipientParty><StandardID schemeAgencyID=\"310\">10013165</StandardID></RecipientParty><SenderParty><InternalID>LBN-GTT-SO-01</InternalID><StandardID schemeAgencyID=\"310\">10000014</StandardID></SenderParty></MessageHeader><TransportationEvent><ObjectReference><TransportationOrderID>1605149943</TransportationOrderID></ObjectReference><Event><Location><InternalID>xri://sap.com/id:LBN#10013165:QM7CLNT910:Location:SP:P_SHANGHAI</InternalID></Location><Stop><Id>00000019240003</Id></Stop><EventTypeCode>SHP_ARRIVAL</EventTypeCode><EventReasonText>0000000000</EventReasonText><ExpectedDateTime>2020-11-22T10:59:03Z</ExpectedDateTime><ActualDateTime>2020-07-10T22:00:00Z</ActualDateTime></Event></TransportationEvent></TransportationEventNotificationMessage></n0:TransportationEventBulkNotification>";
        String targetServiceUrl = "http://alextmqm7.wdf.sap.corp:50030/sap/bc/srt/xip/scmtms/tpevtbntfi/170/lbn_event_in/lbn_event_in";
        HttpHeaders headers = new HttpHeaders();

        boolean useConnectivityProxy = false;

        headers.setBasicAuth("HUEV", "Welcome3");
        RestTemplate restTemplate = this.createInnerRestTemplate(useConnectivityProxy);

        HttpEntity<?> requestEntity = new HttpEntity<>(body,headers);

        return restTemplate.exchange(targetServiceUrl, HttpMethod.POST, requestEntity, responseType, useConnectivityProxy).getBody();
    }

    protected RestTemplate createInnerRestTemplate(boolean useConnectivityProxy){
        RestTemplate result = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout((int)SECONDS.toMillis(60));
        requestFactory.setReadTimeout((int)SECONDS.toMillis(60));
        result.setRequestFactory(requestFactory);

        return result;
    }

    public static void main(String[] args) {
        Util util = new Util();
        util.sendRequest(String.class);
    }
}
