package io.github.fosstree.core;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NullifierTest {

    @Data
    public class ParentClass {
        private String a;
        private String b;
        private ChildClass child;
    }

    @Data
    public class ChildClass {
        private String c;
        private String d;
    }

    @Test
    public void get_WithoutDefaultForProperChain_ReturnsEvaluation() {
        ChildClass child = new ChildClass();
        child.setC("eval");

        ParentClass parent = new ParentClass();
        parent.setChild(child);

        String eval = Nullifier.get(() -> parent.getChild().getC());
        Assert.assertEquals("eval", eval);
    }

    @Test
    public void get_WithoutDefaultForBrokenChain_ReturnsNull() {
        ParentClass parent = new ParentClass();
        String eval = Nullifier.get(() -> parent.getChild().getC());
        Assert.assertNull(eval);
    }

    @Test
    public void get_WithDefaultForProperChain_ReturnsEvaluation() {
        ChildClass child = new ChildClass();
        child.setC("eval");

        ParentClass parent = new ParentClass();
        parent.setChild(child);

        String eval = Nullifier.get(() -> parent.getChild().getC(), "default");
        Assert.assertEquals("eval", eval);
    }

    @Test
    public void get_WithDefaultForBrokenChain_ReturnsDefault() {
        ParentClass parent = new ParentClass();
        String eval = Nullifier.get(() -> parent.getChild().getC(), "default");
        Assert.assertEquals("default", eval);
    }
}
