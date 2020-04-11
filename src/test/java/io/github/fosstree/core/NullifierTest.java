package io.github.fosstree.core;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class NullifierTest {

    @Data
    public class ParentClass {
        private String a;
        private String b;
        private List<String> c = Arrays.asList("c");
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
    public void get_WithoutDefaultForBrokenChainByNullPointer_ReturnsNull() {
        ParentClass parent = new ParentClass();
        String eval = Nullifier.get(() -> parent.getChild().getC());
        Assert.assertNull(eval);
    }

    @Test
    public void get_WithoutDefaultForBrokenChainByIndexOutOfBound_ReturnsNull() {
        ParentClass parent = new ParentClass();
        String eval = Nullifier.get(() -> parent.getC().get(1));
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
    public void get_WithDefaultForBrokenChainByNullPointer_ReturnsDefault() {
        ParentClass parent = new ParentClass();
        String eval = Nullifier.get(() -> parent.getChild().getC(), "default");
        Assert.assertEquals("default", eval);
    }

    @Test
    public void get_WithDefaultForBrokenChainByIndexOutOfBound_ReturnsDefault() {
        ParentClass parent = new ParentClass();
        String eval = Nullifier.get(() -> parent.getC().get(1), "default");
        Assert.assertEquals("default", eval);
    }
}
