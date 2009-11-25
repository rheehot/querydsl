/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.types.path;

import com.mysema.query.types.Visitor;
import com.mysema.query.types.expr.EBoolean;
import com.mysema.query.types.expr.ETime;
import com.mysema.query.util.NotEmpty;

/**
 * @author tiwe
 *
 * @param <D>
 */
@SuppressWarnings({"unchecked","serial"})
public class PTime<D extends Comparable> extends ETime<D> implements Path<D>{

    private final Path<D> pathMixin;
    
    public PTime(Class<? extends D> type, Path<?> parent, @NotEmpty String property) {
        this(type, PathMetadata.forProperty(parent, property));
    }

    public PTime(Class<? extends D> type, PathMetadata<?> metadata) {
        super(type);
        this.pathMixin = new PathMixin<D>(this, metadata);
    }
    
    public PTime(Class<? extends D> type, @NotEmpty String var) {
        this(type, PathMetadata.forVariable(var));
    }    
    @Override
    public void accept(Visitor v) {
        v.visit(this);        
    }

    @Override
    public ETime<D> asExpr() {
        return this;
    }
    
    @Override
    public boolean equals(Object o) {
        return pathMixin.equals(o);
    }
    
    @Override
    public PathMetadata<?> getMetadata() {
        return pathMixin.getMetadata();
    }

    @Override
    public Path<?> getRoot() {
        return pathMixin.getRoot();
    }

    @Override
    public int hashCode() {
        return pathMixin.hashCode();
    }

    @Override
    public EBoolean isNotNull() {
        return pathMixin.isNotNull();
    }
    
    @Override
    public EBoolean isNull() {
        return pathMixin.isNull();
    }
}
