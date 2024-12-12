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

package com.sogeor.framework.function;

import com.sogeor.framework.annotation.Contract;
import com.sogeor.framework.annotation.NonNull;
import com.sogeor.framework.validation.ValidationFault;
import com.sogeor.framework.validation.Validator;

/**
 * Представляет собой действие (1).
 *
 * @param <F> тип программного сбоя или неисправности, возникающей во время выполнения [1].
 *
 * @since 1.0.0-RC1
 */
@FunctionalInterface
public interface Action<F extends Throwable> {

    /**
     * Создаёт действие (1) с пустым методом {@linkplain #perform()}.
     *
     * @param <F> тип программного сбоя или неисправности, возникающей во время выполнения [1].
     *
     * @return [1].
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    static <F extends Throwable> @NonNull Action<F> empty() {
        return () -> {};
    }

    /**
     * Возвращает [1].
     *
     * @param action действие (1).
     * @param <F> тип программного сбоя или неисправности, возникающей во время выполнения [1].
     *
     * @return [1].
     *
     * @apiNote Предназначен для удобного создания лямбда-выражений.
     * @since 1.0.0-RC1
     */
    @Contract("? -> 1")
    static <F extends Throwable> @NonNull Action<F> of(final @NonNull Action<F> action) {
        return action;
    }

    /**
     * Выполняет это действие (1).
     *
     * @throws ValidationFault неудачная валидация.
     * @throws F неудачное выполнение [1].
     * @since 1.0.0-RC1
     */
    @Contract("-> ?")
    void perform() throws ValidationFault, F;

    /**
     * Создаёт действие (2) с методом {@linkplain #perform()}, выполняющим сначала метод
     * {@linkplain #perform() this.perform()}, а потом метод {@linkplain #perform() action.perform()}.
     *
     * @param action действие (1).
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Action<F> and(final @NonNull Action<? extends F> action) throws ValidationFault {
        Validator.nonNull(action, "The passed callback");
        return () -> {
            perform();
            action.perform();
        };
    }

    /**
     * Создаёт действие (2) с методом {@linkplain #perform()}, пытающимся выполнить сначала метод
     * {@linkplain #perform() this.perform()}, а потом, если неудачно, метод {@linkplain #perform() action.perform()}.
     *
     * @param action действие (1).
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Action<F> or(final @NonNull Action<? extends F> action) throws ValidationFault {
        Validator.nonNull(action, "The passed callback");
        return () -> {
            try {
                perform();
            } catch (final @NonNull Throwable primary) {
                try {
                    action.perform();
                } catch (final @NonNull Throwable secondary) {
                    primary.addSuppressed(secondary);
                    throw primary;
                }
            }
        };
    }

}
