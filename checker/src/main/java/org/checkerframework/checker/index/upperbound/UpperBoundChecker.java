package org.checkerframework.checker.index.upperbound;

import java.util.Set;
import javax.lang.model.element.ExecutableElement;
import org.checkerframework.checker.index.inequality.LessThanChecker;
import org.checkerframework.checker.index.lowerbound.LowerBoundChecker;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.LTOMLengthOf;
import org.checkerframework.checker.index.qual.SubstringIndexFor;
import org.checkerframework.checker.index.qual.UpperBoundLiteral;
import org.checkerframework.checker.index.samelen.SameLenChecker;
import org.checkerframework.checker.index.searchindex.SearchIndexChecker;
import org.checkerframework.checker.index.substringindex.SubstringIndexChecker;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.signature.qual.FullyQualifiedName;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.common.value.ValueChecker;
import org.checkerframework.framework.qual.RelevantJavaTypes;
import org.checkerframework.framework.source.SourceChecker;
import org.checkerframework.framework.source.SuppressWarningsPrefix;
import org.checkerframework.javacutil.TreeUtils;

/**
 * A type-checker for preventing arrays from being accessed with values that are too high.
 *
 * @checker_framework.manual #index-checker Index Checker
 */
@RelevantJavaTypes({
  Byte.class,
  Short.class,
  Integer.class,
  Long.class,
  Character.class,
  byte.class,
  short.class,
  int.class,
  long.class,
  char.class,
})
@SuppressWarningsPrefix({"index", "upperbound"})
public class UpperBoundChecker extends BaseTypeChecker {
  /** The SubstringIndexFor.value argument/element. */
  public @MonotonicNonNull ExecutableElement substringIndexForValueElement;

  /** The SubstringIndexFor.offset argument/element. */
  public @MonotonicNonNull ExecutableElement substringIndexForOffsetElement;

  /** The LTLengthOf.value argument/element. */
  public @MonotonicNonNull ExecutableElement ltLengthOfValueElement;

  /** The LTLengthOf.offset argument/element. */
  public @MonotonicNonNull ExecutableElement ltLengthOfOffsetElement;

  /** The LTEqLengthOf.value argument/element. */
  public @MonotonicNonNull ExecutableElement ltEqLengthOfValueElement;

  /** The LTOMLengthOf.value argument/element. */
  public @MonotonicNonNull ExecutableElement ltOMLengthOfValueElement;

  /** The UpperBoundLiteral.value element/field. */
  public @MonotonicNonNull ExecutableElement upperBoundLiteralValueElement;

  /** Create a new UpperBoundChecker. */
  public UpperBoundChecker() {}

  @Override
  public void initChecker() {
    super.initChecker();
    substringIndexForValueElement =
        TreeUtils.getMethod(SubstringIndexFor.class, "value", 0, processingEnv);
    substringIndexForOffsetElement =
        TreeUtils.getMethod(SubstringIndexFor.class, "offset", 0, processingEnv);
    ltLengthOfValueElement = TreeUtils.getMethod(LTLengthOf.class, "value", 0, processingEnv);
    ltLengthOfOffsetElement = TreeUtils.getMethod(LTLengthOf.class, "offset", 0, processingEnv);
    ltEqLengthOfValueElement = TreeUtils.getMethod(LTEqLengthOf.class, "value", 0, processingEnv);
    ltOMLengthOfValueElement = TreeUtils.getMethod(LTOMLengthOf.class, "value", 0, processingEnv);
    upperBoundLiteralValueElement =
        TreeUtils.getMethod(UpperBoundLiteral.class, "value", 0, processingEnv);
  }

  @Override
  public boolean shouldSkipUses(@FullyQualifiedName String typeName) {
    return super.shouldSkipUses(typeName);
  }

  @Override
  protected Set<Class<? extends SourceChecker>> getImmediateSubcheckerClasses() {
    Set<Class<? extends SourceChecker>> checkers = super.getImmediateSubcheckerClasses();
    checkers.add(SubstringIndexChecker.class);
    checkers.add(SearchIndexChecker.class);
    checkers.add(SameLenChecker.class);
    checkers.add(LowerBoundChecker.class);
    checkers.add(ValueChecker.class);
    checkers.add(LessThanChecker.class);
    return checkers;
  }
}
