/*******************************************************************************
 *
 * Pentaho Big Data
 *
 * Copyright (C) 2002-2014 by Pentaho : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.hbase.shim.cdh53;

import org.pentaho.hbase.shim.common.CommonHBaseConnection;
import org.pentaho.hbase.shim.cdh53.wrapper.HBaseConnectionInterface;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;

public class HBaseConnectionImpl extends CommonHBaseConnection implements HBaseConnectionInterface {

  @Override
  public Class<?> getByteArrayComparableClass() throws ClassNotFoundException {
    return Class.forName( "org.apache.hadoop.hbase.filter.ByteArrayComparable" );
  }

  @Override
  public Class<?> getCompressionAlgorithmClass() throws ClassNotFoundException {
    return Class.forName( "org.apache.hadoop.hbase.io.compress.Compression.Algorithm" );
  }

  @Override
  public Class<?> getBloomTypeClass() throws ClassNotFoundException {
    return Class.forName( "org.apache.hadoop.hbase.regionserver.BloomType" );
  }

  @Override
  public Class<?> getDeserializedNumericComparatorClass() throws ClassNotFoundException {
    return Class.forName( "org.pentaho.hbase.shim.cdh53.DeserializedNumericComparator" );
  }

  @Override
  public Class<?> getDeserializedBooleanComparatorClass() throws ClassNotFoundException {
    return Class.forName( "org.pentaho.hbase.shim.cdh53.DeserializedBooleanComparator" );
  }

  protected <T> T doWithContextClassLoader( Callable<T> callable ) throws Exception {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
    try {
      return callable.call();
    } finally {
      Thread.currentThread().setContextClassLoader( cl );
    }
  }

  @Override
  public void createTable( final String tableName, final List<String> colFamilyNames, final Properties creationProps )
    throws Exception {
    doWithContextClassLoader( new Callable<Void>() {

      @Override
      public Void call() throws Exception {

        HBaseConnectionImpl.super.createTable( tableName, colFamilyNames, creationProps );
        return null;
      }
    } );
  }

  @Override
  public void deleteTable( final String tableName ) throws Exception {
    doWithContextClassLoader( new Callable<Void>() {

      @Override
      public Void call() throws Exception {

        HBaseConnectionImpl.super.deleteTable( tableName );
        return null;
      }
    } );
  }

  @Override
  public void disableTable( final String tableName ) throws Exception {
    doWithContextClassLoader( new Callable<Void>() {

      @Override
      public Void call() throws Exception {

        HBaseConnectionImpl.super.disableTable( tableName );
        return null;
      }
    } );
  }

  @Override
  public void enableTable( final String tableName ) throws Exception {
    doWithContextClassLoader( new Callable<Void>() {

      @Override
      public Void call() throws Exception {

        HBaseConnectionImpl.super.enableTable( tableName );
        return null;
      }
    } );
  }

  @Override
  public List<String> getTableFamiles( final String tableName ) throws Exception {
    return doWithContextClassLoader( new Callable<List<String>>() {

      @Override
      public List<String> call() throws Exception {
        return HBaseConnectionImpl.super.getTableFamiles( tableName );
      }
    } );
  }

  @Override
  public boolean isTableAvailable( final String tableName ) throws Exception {
    return doWithContextClassLoader( new Callable<Boolean>() {

      @Override
      public Boolean call() throws Exception {
        return HBaseConnectionImpl.super.isTableAvailable( tableName );
      }
    } );
  }

  @Override
  public boolean isTableDisabled( final String tableName ) throws Exception {
    return doWithContextClassLoader( new Callable<Boolean>() {

      @Override
      public Boolean call() throws Exception {
        return HBaseConnectionImpl.super.isTableDisabled( tableName );
      }
    } );
  }

  @Override
  public List<String> listTableNames() throws Exception {
    return doWithContextClassLoader( new Callable<List<String>>() {

      @Override
      public List<String> call() throws Exception {
        return HBaseConnectionImpl.super.listTableNames();
      }
    } );
  }

  @Override
  public boolean tableExists( final String tableName ) throws Exception {
    return doWithContextClassLoader( new Callable<Boolean>() {

      @Override
      public Boolean call() throws Exception {
        return HBaseConnectionImpl.super.tableExists( tableName );
      }
    } );
  }
}
