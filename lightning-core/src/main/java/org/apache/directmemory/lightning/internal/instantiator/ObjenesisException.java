/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.directmemory.lightning.internal.instantiator;

/**
 * Exception thrown by Objenesis. It wraps any instantiation exceptions. Note that this exception is runtime to prevent
 * having to catch it. It will do normal exception wrapping for JDK 1.4 and more and basic message wrapping for JDK 1.3.
 * 
 * @author Henri Tremblay
 */
public class ObjenesisException
    extends RuntimeException
{

    private static final long serialVersionUID = -2677230016262426968L;

    private static final boolean jdk14 =
        ( Double.parseDouble( System.getProperty( "java.specification.version" ) ) > 1.3 );

    /**
     * @param msg Error message
     */
    public ObjenesisException( String msg )
    {
        super( msg );
    }

    /**
     * @param cause Wrapped exception. The message will be the one of the cause.
     */
    public ObjenesisException( Throwable cause )
    {
        super( cause == null ? null : cause.toString() );
        if ( jdk14 )
        {
            initCause( cause );
        }
    }

    /**
     * @param msg Error message
     * @param cause Wrapped exception
     */
    public ObjenesisException( String msg, Throwable cause )
    {
        super( msg );
        if ( jdk14 )
        {
            initCause( cause );
        }
    }
}
