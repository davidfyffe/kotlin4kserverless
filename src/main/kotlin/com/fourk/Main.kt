package com.fourk

import org.http4k.core.*
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.CorsPolicy
import org.http4k.filter.ServerFilters
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.ApacheServer
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.path
import org.http4k.server.asServer
import org.http4k.serverless.AppLoader
import kotlin.system.measureTimeMillis

val userList = mutableListOf<String>()

val userRoutes = routes(
    "/users" bind Method.GET to { request: Request -> Response(OK).body(userList.toString()) },
    "/users/{user}" bind Method.POST to { request: Request -> userList.add(request.path("user") ?: "default"); Response(OK).body(userList.toString()) }
)

val app =
    routes(
        "/" bind Method.GET to { request : Request -> Response(OK).body("HELLO EE") },
        userRoutes
    )

fun main() {
    app.asServer(ApacheServer(port = 8080)).start()
}

object LambdaHandler : AppLoader {
    override fun invoke(p1: Map<String, String>): HttpHandler {
        return app
    }
}