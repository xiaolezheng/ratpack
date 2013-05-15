package org.ratpackframework.test.groovy

import org.ratpackframework.groovy.routing.Routing
import org.ratpackframework.groovy.routing.internal.RoutingHandler
import org.ratpackframework.routing.Handler
import org.ratpackframework.test.DefaultRatpackSpec

import static org.ratpackframework.groovy.Closures.action

class RatpackGroovyDslSpec extends DefaultRatpackSpec {

  void routing(@DelegatesTo(Routing) Closure<?> configurer) {
    this.routingCallback = configurer
  }

  @Override
  protected Handler createHandler() {
    return new RoutingHandler(action(routingCallback))
  }
}
