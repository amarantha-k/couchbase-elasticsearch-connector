/*
 * Copyright 2018 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.couchbase.connector.elasticsearch;

import com.couchbase.connector.testcontainers.CouchbaseContainer;

import java.io.Closeable;
import java.util.concurrent.atomic.AtomicInteger;

class TempBucket implements Closeable {
  private static final AtomicInteger counter = new AtomicInteger();

  private final CouchbaseContainer couchbase;
  private final String bucketName;

  public TempBucket(CouchbaseContainer couchbase) {
    this.couchbase = couchbase;
    this.bucketName = "temp-" + counter.getAndIncrement();
    couchbase.createBucket(bucketName);
  }

  @Override
  public void close() {
    couchbase.deleteBucket(bucketName);
  }

  public String name() {
    return bucketName;
  }
}
