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

package com.sogeor.validation.variable;

import com.sogeor.validation.ValidationFault;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Представляет собой непроверяемую неисправность программы, возникающую из-за ошибки валидации переменной.
 *
 * @since 1.0.0-RC1
 */
public class VariableValidationFault extends ValidationFault {

    /**
     * Представляет собой сообщение по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    public static final @NotNull String DEFAULT_MESSAGE = "Failed to validate the passed variable";

    /**
     * Представляет собой конструктор по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public VariableValidationFault() {
        super(DEFAULT_MESSAGE, DEFAULT_CAUSE, DEFAULT_SUPPRESSION, DEFAULT_STACK_TRACE);
    }

    /**
     * Представляет собой конструктор, позволяющий задать сообщение.
     *
     * @param message сообщение.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public VariableValidationFault(final @Nullable String message) {
        super(message, DEFAULT_CAUSE, DEFAULT_SUPPRESSION, DEFAULT_STACK_TRACE);
    }

    /**
     * Представляет собой конструктор, позволяющий задать причину возникновения.
     *
     * @param cause причина возникновения.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public VariableValidationFault(final @Nullable Throwable cause) {
        super(DEFAULT_MESSAGE, cause, DEFAULT_SUPPRESSION, DEFAULT_STACK_TRACE);
    }

    /**
     * Представляет собой конструктор, позволяющий задать сообщение и причину возникновения.
     *
     * @param message сообщение.
     * @param cause причина возникновения.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public VariableValidationFault(final @Nullable String message, final @Nullable Throwable cause) {
        super(message, cause, DEFAULT_SUPPRESSION, DEFAULT_STACK_TRACE);
    }

    /**
     * Представляет собой конструктор, позволяющий задать параметры подавления и трассировки стека.
     *
     * @param suppression параметр подавления.
     * @param stackTrace параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public VariableValidationFault(final boolean suppression, final boolean stackTrace) {
        super(DEFAULT_MESSAGE, DEFAULT_CAUSE, suppression, stackTrace);
    }

    /**
     * Представляет собой конструктор, позволяющий задать сообщение, причину возникновения, параметры подавления и
     * трассировки стека.
     *
     * @param message сообщение.
     * @param cause причина возникновения.
     * @param suppression параметр подавления.
     * @param stackTrace параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public VariableValidationFault(final @Nullable String message, final @Nullable Throwable cause,
                                   final boolean suppression, final boolean stackTrace) {
        super(message, cause, suppression, stackTrace);
    }

}
