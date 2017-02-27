package com.atlassian.visitor;

/**
 * Created by satheish on 2/26/17.
 */
public interface Visitable {
    public void accept(Visitor vistor);
}
