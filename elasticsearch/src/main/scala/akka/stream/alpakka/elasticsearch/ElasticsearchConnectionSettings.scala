/*
 * Copyright (C) 2016-2020 Lightbend Inc. <https://www.lightbend.com>
 */

package akka.stream.alpakka.elasticsearch

import akka.http.scaladsl.HttpsConnectionContext
import akka.http.scaladsl.model.HttpHeader

final class ElasticsearchConnectionSettings private (
    val baseUrl: String,
    val port: Option[Int],
    val username: Option[String],
    val password: Option[String],
    val headers: Option[List[HttpHeader]],
    val connectionContext: Option[HttpsConnectionContext]
) {

  def withBaseUrl(value: String): ElasticsearchConnectionSettings = copy(baseUrl = value)

  def withPort(value: Int): ElasticsearchConnectionSettings = copy(port = Option(value))

  def hasPortDefined: Boolean = port.isDefined

  def withCredentials(username: String, password: String): ElasticsearchConnectionSettings =
    copy(username = Option(username), password = Option(password))

  def hasCredentialsDefined: Boolean = username.isDefined && password.isDefined

  def withHeaders(headers: List[HttpHeader]): ElasticsearchConnectionSettings =
    copy(headers = Option(headers))

  def hasHeadersDefined: Boolean = headers.isDefined

  def withConnectionContext(connectionContext: HttpsConnectionContext): ElasticsearchConnectionSettings =
    copy(connectionContext = Option(connectionContext))

  def hasConnectionContextDefined: Boolean = connectionContext.isDefined

  def copy(baseUrl: String = baseUrl,
           port: Option[Int] = port,
           username: Option[String] = username,
           password: Option[String] = password,
           headers: Option[List[HttpHeader]] = headers,
           connectionContext: Option[HttpsConnectionContext] = connectionContext): ElasticsearchConnectionSettings =
    new ElasticsearchConnectionSettings(baseUrl = baseUrl,
                                        port = port,
                                        username = username,
                                        password = password,
                                        headers,
                                        connectionContext)

  override def toString =
    s"""ElasticsearchConnectionSettings(baseUrl=$baseUrl,port=$port,username=$username,password=${password.fold("")(
      _ => "***"
    )})"""
}

object ElasticsearchConnectionSettings {

  /** Scala API */
  def apply(baseUrl: String): ElasticsearchConnectionSettings =
    new ElasticsearchConnectionSettings(baseUrl, None, None, None, None, None)

  /** Java API */
  def create(baseUrl: String): ElasticsearchConnectionSettings =
    new ElasticsearchConnectionSettings(baseUrl, None, None, None, None, None)
}
