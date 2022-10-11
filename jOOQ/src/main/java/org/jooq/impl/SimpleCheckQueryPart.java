/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: http://www.jooq.org/licenses
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.jooq.impl;

import org.jooq.Context;
import org.jooq.OrderField;
import org.jooq.QueryPart;
import org.jooq.QueryPartInternal;

/**
 * A marker interface for all query parts that are capable of generating
 * "simple" SQL. This information is used mainly for formatting decisions.
 * <p>
 * Unlike {@link SimpleQueryPart}, which marks a {@link QueryPart} that is
 * unconditionally simple, this allows for checking whether a {@link QueryPart}
 * is {@link #isSimple(Context)}
 *
 * @author Lukas Eder
 */
interface SimpleCheckQueryPart extends QueryPartInternal {

    /**
     * Whether the {@link QueryPart} really is simple.
     * <p>
     * e.g. an {@link OrderField} can be simple if its contents are also simple.
     */
    default boolean isSimple(Context<?> ctx) {
        return true;
    }
}
