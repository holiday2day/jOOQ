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

import static org.jooq.impl.Tools.EMPTY_QUERY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.jooq.Block;
import org.jooq.Configuration;
import org.jooq.Context;
import org.jooq.DSLContext;
import org.jooq.Function1;
import org.jooq.Queries;
import org.jooq.Query;
import org.jooq.ResultQuery;
import org.jooq.Results;
import org.jooq.Traverser;
import org.jooq.impl.DefaultParseContext.IgnoreQuery;
import org.jooq.impl.QOM.MList;
import org.jooq.QueryPart;
import org.jooq.impl.ResultsImpl.ResultOrRowsImpl;

/**
 * @author Lukas Eder
 */
final class QueriesImpl extends AbstractAttachableQueryPart implements Queries {

    private final QueryPartList<Query> queries;

    QueriesImpl(Configuration configuration, Collection<? extends Query> queries) {
        super(configuration);

        this.queries = new QueryPartList<>(queries);
    }

    // ------------------------------------------------------------------------
    // Access API
    // ------------------------------------------------------------------------

    @Override
    public final Queries concat(Queries other) {
        Query[] array = other.queries();
        List<Query> list = new ArrayList<>(queries.size() + array.length);
        list.addAll(queries);
        list.addAll(Arrays.asList(array));
        return new QueriesImpl(configuration(), list);
    }

    @Override
    public final Query[] queries() {
        return queries.toArray(EMPTY_QUERY);
    }

    @Override
    public final Block block() {
        return configurationOrDefault().dsl().begin(queries);
    }

    @Override
    public final Iterator<Query> iterator() {
        return queries.iterator();
    }

    @Override
    public final Stream<Query> stream() {
        return queryStream();
    }

    @Override
    public final Stream<Query> queryStream() {
        return queries.stream();
    }

    // ------------------------------------------------------------------------
    // Execution API
    // ------------------------------------------------------------------------

    @Override
    public final Results fetchMany() {
        Configuration c = configurationOrThrow();
        ResultsImpl results = new ResultsImpl(c);
        DSLContext ctx = c.dsl();

        for (Query query : this)
            if (query instanceof ResultQuery)
                results.resultsOrRows.addAll(ctx.fetchMany((ResultQuery<?>) query).resultsOrRows());
            else
                results.resultsOrRows.add(new ResultOrRowsImpl(ctx.execute(query)));

        return results;
    }

    @Override
    public final int[] executeBatch() {
        return configurationOrThrow().dsl().batch(this).execute();
    }

    // ------------------------------------------------------------------------
    // QueryPart API
    // ------------------------------------------------------------------------

    @Override
    public final void accept(Context<?> ctx) {
        boolean first = true;
        boolean separatorRequired = false;

        for (Query query : this) {
            boolean i = query instanceof IgnoreQuery;

            if (first)
                first = false;
            else if (separatorRequired)
                ctx.formatSeparator();

            if (i)
                if (ctx.format())
                    query = new IgnoreQuery(((IgnoreQuery) query).sql.replaceFirst("^(?sm:\\n? *(.*?) *\\n?)$", "$1"));
                else
                    query = new IgnoreQuery(((IgnoreQuery) query).sql.replaceFirst("^(?sm: *(.*?) *)$", "$1"));

            ctx.visit(query);

            if (!i)
                ctx.sql(';');

            separatorRequired = !i || !((IgnoreQuery) query).sql.endsWith("\n");
        }
    }

    // -------------------------------------------------------------------------
    // XXX: Query Object Model
    // -------------------------------------------------------------------------

    @Override
    public final <R> R $traverse(Traverser<?, R> traverser) {
        return QOM.traverse(traverser, this, $queries());
    }

    @Override
    public final QueryPart $replace(
        Predicate<? super QueryPart> recurse,
        Function1<? super QueryPart, ? extends QueryPart> replacement
    ) {
        return QOM.replace(this, queries, q -> new QueriesImpl(configuration(), q), recurse, replacement);
    }

    @Override
    public final MList<? extends Query> $queries() {
        return queries;
    }

    // ------------------------------------------------------------------------
    // Object API
    // ------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return queries.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof QueriesImpl))
            return false;

        return queries.equals(((QueriesImpl) obj).queries);
    }
}
