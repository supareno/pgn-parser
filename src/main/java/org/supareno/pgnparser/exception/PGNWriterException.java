/*
 * PGNWriterException.java
 *
 * Copyright 2008-2018 supareno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.supareno.pgnparser.exception;

/**
 * Exception used when an exception occurs during write operations.
 *
 * @author supareno
 * @since 3.0.0
 */
public class PGNWriterException extends RuntimeException {

    public PGNWriterException(String message, Throwable cause) {
        super(message, cause);
    }
}
