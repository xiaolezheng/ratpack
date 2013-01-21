/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bleedingwolf.ratpack

class TemplateRenderingSpec extends RatpackSpec {

  def "can render template"() {
    given:
    templateFile("foo.html") << "\${value}"

    and:
    ratpackFile << """
      get("/") {
        render "foo.html", value: "bar"
      }
    """

    when:
    app.start()

    then:
    urlText() == "bar"
  }


  def "can render inner template"() {
    given:
    templateFile("outer.html") << "outer: \${value}, \${render 'inner.html', value: 'inner'}"
    templateFile("inner.html") << "inner: \${value}"

    and:
    ratpackFile << """
      get("/") {
        render "outer.html", value: "outer"
      }
    """

    when:
    app.start()

    then:
    urlText() == "outer: outer, inner: inner"
  }

}
