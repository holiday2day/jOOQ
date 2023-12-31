/*
 * This file is generated by jOOQ.
 */
package org.jooq.meta.mysql.information_schema.tables;


import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.meta.mysql.information_schema.InformationSchema;
import org.jooq.types.UInteger;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Parameters extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>information_schema.PARAMETERS</code>
     */
    public static final Parameters PARAMETERS = new Parameters();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>information_schema.PARAMETERS.SPECIFIC_CATALOG</code>.
     */
    public final TableField<Record, String> SPECIFIC_CATALOG = createField(DSL.name("SPECIFIC_CATALOG"), SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>information_schema.PARAMETERS.SPECIFIC_SCHEMA</code>.
     */
    public final TableField<Record, String> SPECIFIC_SCHEMA = createField(DSL.name("SPECIFIC_SCHEMA"), SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>information_schema.PARAMETERS.SPECIFIC_NAME</code>.
     */
    public final TableField<Record, String> SPECIFIC_NAME = createField(DSL.name("SPECIFIC_NAME"), SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>information_schema.PARAMETERS.ORDINAL_POSITION</code>.
     */
    public final TableField<Record, ULong> ORDINAL_POSITION = createField(DSL.name("ORDINAL_POSITION"), SQLDataType.BIGINTUNSIGNED.nullable(false).defaultValue(DSL.inline("0", SQLDataType.BIGINTUNSIGNED)), this, "");

    /**
     * The column <code>information_schema.PARAMETERS.PARAMETER_MODE</code>.
     */
    public final TableField<Record, String> PARAMETER_MODE = createField(DSL.name("PARAMETER_MODE"), SQLDataType.VARCHAR(5), this, "");

    /**
     * The column <code>information_schema.PARAMETERS.PARAMETER_NAME</code>.
     */
    public final TableField<Record, String> PARAMETER_NAME = createField(DSL.name("PARAMETER_NAME"), SQLDataType.VARCHAR(64), this, "");

    /**
     * The column <code>information_schema.PARAMETERS.DATA_TYPE</code>.
     */
    public final TableField<Record, String> DATA_TYPE = createField(DSL.name("DATA_TYPE"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>information_schema.PARAMETERS.CHARACTER_MAXIMUM_LENGTH</code>.
     */
    public final TableField<Record, Long> CHARACTER_MAXIMUM_LENGTH = createField(DSL.name("CHARACTER_MAXIMUM_LENGTH"), SQLDataType.BIGINT, this, "");

    /**
     * The column
     * <code>information_schema.PARAMETERS.CHARACTER_OCTET_LENGTH</code>.
     */
    public final TableField<Record, Long> CHARACTER_OCTET_LENGTH = createField(DSL.name("CHARACTER_OCTET_LENGTH"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>information_schema.PARAMETERS.NUMERIC_PRECISION</code>.
     */
    public final TableField<Record, UInteger> NUMERIC_PRECISION = createField(DSL.name("NUMERIC_PRECISION"), SQLDataType.INTEGERUNSIGNED, this, "");

    /**
     * The column <code>information_schema.PARAMETERS.NUMERIC_SCALE</code>.
     */
    public final TableField<Record, Long> NUMERIC_SCALE = createField(DSL.name("NUMERIC_SCALE"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>information_schema.PARAMETERS.DATETIME_PRECISION</code>.
     */
    public final TableField<Record, UInteger> DATETIME_PRECISION = createField(DSL.name("DATETIME_PRECISION"), SQLDataType.INTEGERUNSIGNED, this, "");

    /**
     * The column <code>information_schema.PARAMETERS.CHARACTER_SET_NAME</code>.
     */
    public final TableField<Record, String> CHARACTER_SET_NAME = createField(DSL.name("CHARACTER_SET_NAME"), SQLDataType.VARCHAR(64), this, "");

    /**
     * The column <code>information_schema.PARAMETERS.COLLATION_NAME</code>.
     */
    public final TableField<Record, String> COLLATION_NAME = createField(DSL.name("COLLATION_NAME"), SQLDataType.VARCHAR(64), this, "");

    /**
     * The column <code>information_schema.PARAMETERS.DTD_IDENTIFIER</code>.
     */
    public final TableField<Record, String> DTD_IDENTIFIER = createField(DSL.name("DTD_IDENTIFIER"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>information_schema.PARAMETERS.ROUTINE_TYPE</code>.
     */
    public final TableField<Record, String> ROUTINE_TYPE = createField(DSL.name("ROUTINE_TYPE"), SQLDataType.VARCHAR(9).nullable(false), this, "");

    private Parameters(Name alias, Table<Record> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Parameters(Name alias, Table<Record> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>information_schema.PARAMETERS</code> table
     * reference
     */
    public Parameters(String alias) {
        this(DSL.name(alias), PARAMETERS);
    }

    /**
     * Create an aliased <code>information_schema.PARAMETERS</code> table
     * reference
     */
    public Parameters(Name alias) {
        this(alias, PARAMETERS);
    }

    /**
     * Create a <code>information_schema.PARAMETERS</code> table reference
     */
    public Parameters() {
        this(DSL.name("PARAMETERS"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public Parameters as(String alias) {
        return new Parameters(DSL.name(alias), this);
    }

    @Override
    public Parameters as(Name alias) {
        return new Parameters(alias, this);
    }

    @Override
    public Parameters as(Table<?> alias) {
        return new Parameters(alias.getQualifiedName(), this);
    }
}
