/*
 * Copyright 2024 Sogeor
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
 */

package com.sogeor.validation;

import com.sogeor.annotation.NonNull;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

final class TrueValidationFaultTest {

    @Test
    void defaultMessageField() {
        assertNotNull(TrueValidationFault.DEFAULT_MESSAGE);
    }

    @Test
    void templateMessageField() {
        assertNotNull(TrueValidationFault.TEMPLATE_MESSAGE);
    }

    @Test
    void defaultCauseField() {
        assertNull(TrueValidationFault.DEFAULT_CAUSE);
    }

    @Test
    void defaultSuppressionField() {
        assertTrue(TrueValidationFault.DEFAULT_SUPPRESSION);
    }

    @Test
    void defaultStackTraceField() {
        assertTrue(TrueValidationFault.DEFAULT_STACK_TRACE);
    }

    @Test
    void defaultConstructor() {
        final @NonNull var instance = new TrueValidationFault();
        final @NonNull var exception = new Exception();
        instance.addSuppressed(exception);
        assertEquals(TrueValidationFault.DEFAULT_MESSAGE, instance.getMessage());
        assertEquals(TrueValidationFault.DEFAULT_CAUSE, instance.getCause());
        assertTrue(Arrays.asList(instance.getSuppressed()).contains(exception));
        assertNotEquals(0, instance.getStackTrace().length);
    }

    @Test
    void constructorWithMessage() {
        final @NonNull var message = "The test message";
        final @NonNull var instance = new TrueValidationFault(message);
        final @NonNull var exception = new Exception();
        instance.addSuppressed(exception);
        assertEquals(message, instance.getMessage());
        assertEquals(TrueValidationFault.DEFAULT_CAUSE, instance.getCause());
        assertTrue(Arrays.asList(instance.getSuppressed()).contains(exception));
        assertNotEquals(0, instance.getStackTrace().length);
    }

    @Test
    void constructorWithCause() {
        final @NonNull var cause = new Exception();
        final @NonNull var instance = new TrueValidationFault(cause);
        final @NonNull var exception = new Exception();
        instance.addSuppressed(exception);
        assertEquals(TrueValidationFault.DEFAULT_MESSAGE, instance.getMessage());
        assertEquals(cause, instance.getCause());
        assertTrue(Arrays.asList(instance.getSuppressed()).contains(exception));
        assertNotEquals(0, instance.getStackTrace().length);
    }

    @Test
    void constructorWithMessageAndCause() {
        final @NonNull var message = "The test message";
        final @NonNull var cause = new Exception();
        final @NonNull var instance = new TrueValidationFault(message, cause);
        final @NonNull var exception = new Exception();
        instance.addSuppressed(exception);
        assertEquals(message, instance.getMessage());
        assertEquals(cause, instance.getCause());
        assertTrue(Arrays.asList(instance.getSuppressed()).contains(exception));
        assertNotEquals(0, instance.getStackTrace().length);
    }

    @Test
    void constructorWithSuppressionAndStackTrace() {
        final @NonNull var instance = new TrueValidationFault(false, false);
        final @NonNull var exception = new Exception();
        instance.addSuppressed(exception);
        assertEquals(TrueValidationFault.DEFAULT_MESSAGE, instance.getMessage());
        assertEquals(TrueValidationFault.DEFAULT_CAUSE, instance.getCause());
        assertEquals(0, instance.getSuppressed().length);
        assertEquals(0, instance.getStackTrace().length);
    }

    @Test
    void constructorWithMessageAndCauseAndSuppressionAndStackTrace() {
        final @NonNull var message = "The test message";
        final @NonNull var cause = new Exception();
        final @NonNull var instance = new TrueValidationFault(message, cause, false, false);
        final @NonNull var exception = new Exception();
        instance.addSuppressed(exception);
        assertEquals(message, instance.getMessage());
        assertEquals(cause, instance.getCause());
        assertEquals(0, instance.getSuppressed().length);
        assertEquals(0, instance.getStackTrace().length);
    }

}
