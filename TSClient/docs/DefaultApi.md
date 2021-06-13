# DefaultApi

All URIs are relative to *http://localhost/TSTool-api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getTimeseriesDefinitionByAP**](DefaultApi.md#getTimeseriesDefinitionByAP) | **POST** /timeseriesDefinitionByAP | Get timeseries-Definition for requested AccessParameter
[**getTimeseriesDefinitionByAPs**](DefaultApi.md#getTimeseriesDefinitionByAPs) | **POST** /timeseriesDefinitionByAPs | Get timeseries-Definitions for requested AccessParameters
[**getTimeseriesValuesByTSHead**](DefaultApi.md#getTimeseriesValuesByTSHead) | **POST** /timeseriesValuesByTSHead | Get timeseries values for requested TimeSeriesHead
[**getTimeseriesValuesByTSHeads**](DefaultApi.md#getTimeseriesValuesByTSHeads) | **POST** /timeseriesValuesByTSHeads | Get timeseries values for requested TimeseriesHeads
[**putTimeseriesDefinitionByAP**](DefaultApi.md#putTimeseriesDefinitionByAP) | **PUT** /timeseriesDefinitionByAP | Create a new TimeseriesDefinition
[**putTimeseriesDefinitionsByAPs**](DefaultApi.md#putTimeseriesDefinitionsByAPs) | **PUT** /timeseriesDefinitionByAPs | Create new TimeseriesDefinitions
[**putTimeseriesValuesByTSHead**](DefaultApi.md#putTimeseriesValuesByTSHead) | **PUT** /timeseriesValuesByTSHead | Adds values to given Timeseries. The Timeseries must exists!
[**putTimeseriesValuesByTSHeads**](DefaultApi.md#putTimeseriesValuesByTSHeads) | **PUT** /timeseriesValuesByTSHeads | Adds values to given Timeseries&#39;s. All Timeseries&#39;s must exists!


<a name="getTimeseriesDefinitionByAP"></a>
# **getTimeseriesDefinitionByAP**
> TimeseriesHead getTimeseriesDefinitionByAP(accessParameter)

Get timeseries-Definition for requested AccessParameter

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
AccessParameter accessParameter = new AccessParameter(); // AccessParameter | 
try {
    TimeseriesHead result = apiInstance.getTimeseriesDefinitionByAP(accessParameter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getTimeseriesDefinitionByAP");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accessParameter** | [**AccessParameter**](AccessParameter.md)|  | [optional]

### Return type

[**TimeseriesHead**](TimeseriesHead.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTimeseriesDefinitionByAPs"></a>
# **getTimeseriesDefinitionByAPs**
> List&lt;TimeseriesHead&gt; getTimeseriesDefinitionByAPs(accessParameters)

Get timeseries-Definitions for requested AccessParameters

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
List<AccessParameter> accessParameters = Arrays.asList(new AccessParameter()); // List<AccessParameter> | 
try {
    List<TimeseriesHead> result = apiInstance.getTimeseriesDefinitionByAPs(accessParameters);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getTimeseriesDefinitionByAPs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accessParameters** | [**List&lt;AccessParameter&gt;**](AccessParameter.md)|  | [optional]

### Return type

[**List&lt;TimeseriesHead&gt;**](TimeseriesHead.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTimeseriesValuesByTSHead"></a>
# **getTimeseriesValuesByTSHead**
> Timeseries getTimeseriesValuesByTSHead(timeseriesHead, interval)

Get timeseries values for requested TimeSeriesHead

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
TimeseriesHead timeseriesHead = new TimeseriesHead(); // TimeseriesHead | 
String interval = "interval_example"; // String | 
try {
    Timeseries result = apiInstance.getTimeseriesValuesByTSHead(timeseriesHead, interval);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getTimeseriesValuesByTSHead");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **timeseriesHead** | [**TimeseriesHead**](TimeseriesHead.md)|  | [optional]
 **interval** | **String**|  | [optional]

### Return type

[**Timeseries**](Timeseries.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTimeseriesValuesByTSHeads"></a>
# **getTimeseriesValuesByTSHeads**
> List&lt;Timeseries&gt; getTimeseriesValuesByTSHeads(timeseriesHeads, interval)

Get timeseries values for requested TimeseriesHeads

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
List<TimeseriesHead> timeseriesHeads = Arrays.asList(new TimeseriesHead()); // List<TimeseriesHead> | 
String interval = "interval_example"; // String | 
try {
    List<Timeseries> result = apiInstance.getTimeseriesValuesByTSHeads(timeseriesHeads, interval);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getTimeseriesValuesByTSHeads");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **timeseriesHeads** | [**List&lt;TimeseriesHead&gt;**](TimeseriesHead.md)|  | [optional]
 **interval** | **String**|  | [optional]

### Return type

[**List&lt;Timeseries&gt;**](Timeseries.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="putTimeseriesDefinitionByAP"></a>
# **putTimeseriesDefinitionByAP**
> TimeseriesHead putTimeseriesDefinitionByAP(timeseriesHead)

Create a new TimeseriesDefinition

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
TimeseriesHead timeseriesHead = new TimeseriesHead(); // TimeseriesHead | 
try {
    TimeseriesHead result = apiInstance.putTimeseriesDefinitionByAP(timeseriesHead);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#putTimeseriesDefinitionByAP");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **timeseriesHead** | [**TimeseriesHead**](TimeseriesHead.md)|  | [optional]

### Return type

[**TimeseriesHead**](TimeseriesHead.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="putTimeseriesDefinitionsByAPs"></a>
# **putTimeseriesDefinitionsByAPs**
> List&lt;TimeseriesHead&gt; putTimeseriesDefinitionsByAPs(timeseriesHeads)

Create new TimeseriesDefinitions

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
List<TimeseriesHead> timeseriesHeads = Arrays.asList(new TimeseriesHead()); // List<TimeseriesHead> | 
try {
    List<TimeseriesHead> result = apiInstance.putTimeseriesDefinitionsByAPs(timeseriesHeads);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#putTimeseriesDefinitionsByAPs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **timeseriesHeads** | [**List&lt;TimeseriesHead&gt;**](TimeseriesHead.md)|  | [optional]

### Return type

[**List&lt;TimeseriesHead&gt;**](TimeseriesHead.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="putTimeseriesValuesByTSHead"></a>
# **putTimeseriesValuesByTSHead**
> Timeseries putTimeseriesValuesByTSHead(timeseries)

Adds values to given Timeseries. The Timeseries must exists!

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Timeseries timeseries = new Timeseries(); // Timeseries | 
try {
    Timeseries result = apiInstance.putTimeseriesValuesByTSHead(timeseries);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#putTimeseriesValuesByTSHead");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **timeseries** | [**Timeseries**](Timeseries.md)|  | [optional]

### Return type

[**Timeseries**](Timeseries.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="putTimeseriesValuesByTSHeads"></a>
# **putTimeseriesValuesByTSHeads**
> List&lt;Timeseries&gt; putTimeseriesValuesByTSHeads(timeseriesValues)

Adds values to given Timeseries&#39;s. All Timeseries&#39;s must exists!

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
List<Timeseries> timeseriesValues = Arrays.asList(new Timeseries()); // List<Timeseries> | 
try {
    List<Timeseries> result = apiInstance.putTimeseriesValuesByTSHeads(timeseriesValues);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#putTimeseriesValuesByTSHeads");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **timeseriesValues** | [**List&lt;Timeseries&gt;**](Timeseries.md)|  | [optional]

### Return type

[**List&lt;Timeseries&gt;**](Timeseries.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

