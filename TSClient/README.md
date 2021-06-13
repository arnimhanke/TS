# swagger-java-client

TSTool-service
- API version: 1.0.0
  - Build date: 2020-05-31T10:39:36.107Z

No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)


*Automatically generated by the [Swagger Codegen](https://github.com/swagger-api/swagger-codegen)*


## Requirements

Building the API client library requires:
1. Java 1.7+
2. Maven/Gradle

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>io.swagger</groupId>
  <artifactId>swagger-java-client</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/swagger-java-client-1.0.0.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import de.hanke.client.ApiException;import de.hanke.client.model.AccessParameter;
import de.hanke.client.api.DefaultApi;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        AccessParameter accessParameter = new AccessParameter(); // AccessParameter | 
        try {
            TimeseriesHead result = apiInstance.getTimeseriesDefinitionByAP(accessParameter);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#getTimeseriesDefinitionByAP");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://localhost/TSTool-api*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**getTimeseriesDefinitionByAP**](docs/DefaultApi.md#getTimeseriesDefinitionByAP) | **POST** /timeseriesDefinitionByAP | Get timeseries-Definition for requested AccessParameter
*DefaultApi* | [**getTimeseriesDefinitionByAPs**](docs/DefaultApi.md#getTimeseriesDefinitionByAPs) | **POST** /timeseriesDefinitionByAPs | Get timeseries-Definitions for requested AccessParameters
*DefaultApi* | [**getTimeseriesValuesByTSHead**](docs/DefaultApi.md#getTimeseriesValuesByTSHead) | **POST** /timeseriesValuesByTSHead | Get timeseries values for requested TimeSeriesHead
*DefaultApi* | [**getTimeseriesValuesByTSHeads**](docs/DefaultApi.md#getTimeseriesValuesByTSHeads) | **POST** /timeseriesValuesByTSHeads | Get timeseries values for requested TimeseriesHeads
*DefaultApi* | [**putTimeseriesDefinitionByAP**](docs/DefaultApi.md#putTimeseriesDefinitionByAP) | **PUT** /timeseriesDefinitionByAP | Create a new TimeseriesDefinition
*DefaultApi* | [**putTimeseriesDefinitionsByAPs**](docs/DefaultApi.md#putTimeseriesDefinitionsByAPs) | **PUT** /timeseriesDefinitionByAPs | Create new TimeseriesDefinitions
*DefaultApi* | [**putTimeseriesValuesByTSHead**](docs/DefaultApi.md#putTimeseriesValuesByTSHead) | **PUT** /timeseriesValuesByTSHead | Adds values to given Timeseries. The Timeseries must exists!
*DefaultApi* | [**putTimeseriesValuesByTSHeads**](docs/DefaultApi.md#putTimeseriesValuesByTSHeads) | **PUT** /timeseriesValuesByTSHeads | Adds values to given Timeseries&#39;s. All Timeseries&#39;s must exists!


## Documentation for Models

 - [AccessParameter](docs/AccessParameter.md)
 - [Error](docs/Error.md)
 - [Raster](docs/Raster.md)
 - [Timeseries](docs/Timeseries.md)
 - [TimeseriesHead](docs/TimeseriesHead.md)
 - [TimeseriesUnit](docs/TimeseriesUnit.md)
 - [TimeseriesValue](docs/TimeseriesValue.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author


