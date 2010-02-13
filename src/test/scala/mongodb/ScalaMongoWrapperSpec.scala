/**
 * Copyright (c) 2010, Novus Partners, Inc. <http://novus.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * NOTICE: Portions of this work are derived from the Apache License 2.0 "mongo-scala-driver" work
 * by Alexander Azarov <azarov@osinka.ru>, available from http://github.com/alaz/mongo-scala-driver
 */

package com.novus.test.mongodb

import com.novus.mongodb._
import Implicits.{mongoDBAsScala, mongoConnAsScala, mongoCollAsScala, mongoCursorAsScala}
import com.mongodb._
import org.scalatest.{GivenWhenThen, FeatureSpec}

class ScalaMongoWrapperSpec extends FeatureSpec with GivenWhenThen {
    feature("The implicit extension methods allow for receiving wrapped versions of the Mongo Java objects.") {

      info("the Scala wrappers should provide .asScala methods on each of the Mongo Java connection-related objects.")

      val conn = new Mongo()
      scenario("A Mongo connection object can be converted into a ScalaMongoConn wrapper instance.") {
        given("A Mongo object connected to the default [localhost]")
        assert(conn != null)
        when("The asScala extension method is invoked.")
        val scalaConn = conn.asScala
        then("A ScalaMongoConn wrapper instance is returned.")
        assert(scalaConn != null)
        assert(scalaConn.isInstanceOf[ScalaMongoConn])
        and("The underlying connection is the Java mongo connection object.")
        assert(scalaConn.underlying == conn)
      }

      val db = conn.getDB("test")
      scenario("A DB (database handle) object can be converted into a ScalaMongoDB wrapper instance.") {
        given("A Mongo DB handle upon the test db")
        assert(db != null)
        when("The asScala extension method is invoked.")
        val scalaDB = db.asScala
        then("A ScalaMongoDB wrapper instance is returned.")
        assert(scalaDB != null)
        assert(scalaDB.isInstanceOf[ScalaMongoDB])
        and("The underlying db is the Java mongo db object.")
        assert(scalaDB.underlying == db)
      }

      val coll = db.getCollection("foo")
      scenario("A DBCollection object can be converted into a ScalaMongoCollection wrapper instance.") {
        given("A Mongo DBCollection handle upon the test.foo collection")
        assert(coll != null)
        when("The asScala extension method is invoked.")
        val scalaColl = coll.asScala
        then("A ScalaMongoCollection wrapper instance is returned.")
        assert(scalaColl != null)
        assert(scalaColl.isInstanceOf[ScalaMongoCollection])
        and("The underlying collection is the Java mongo collection object.")
        assert(scalaColl.underlying == coll)
      }
    }


}