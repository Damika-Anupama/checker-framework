package org.checkerframework.checker.index.lowerbound;

import java.util.Set;
import org.checkerframework.checker.index.inequality.LessThanChecker;
import org.checkerframework.checker.index.searchindex.SearchIndexChecker;
import org.checkerframework.checker.signature.qual.FullyQualifiedName;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.common.value.ValueChecker;
import org.checkerframework.framework.qual.RelevantJavaTypes;
import org.checkerframework.framework.source.SourceChecker;
import org.checkerframework.framework.source.SuppressWarningsPrefix;

/**
 * A type-checker for preventing fixed-length sequences such as arrays or strings from being
 * accessed with values that are too low. Normally bundled as part of the Index Checker.
 *
 * @checker_framework.manual #index-checker Index Checker
 */
@SuppressWarningsPrefix({"index", "lowerbound"})
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
public class LowerBoundChecker extends BaseTypeChecker {

  /**
   * A type-checker for preventing fixed-length sequences such as arrays or strings from being
   * accessed with values that are too low. Normally bundled as part of the Index Checker.
   */
  public LowerBoundChecker() {}

  @Override
  public boolean shouldSkipUses(@FullyQualifiedName String typeName) {
    return super.shouldSkipUses(typeName);
  }

  @Override
  protected Set<Class<? extends SourceChecker>> getImmediateSubcheckerClasses() {
    Set<Class<? extends SourceChecker>> checkers = super.getImmediateSubcheckerClasses();
    checkers.add(ValueChecker.class);
    checkers.add(LessThanChecker.class);
    checkers.add(SearchIndexChecker.class);
    return checkers;
  }
}
