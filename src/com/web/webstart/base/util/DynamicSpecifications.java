package com.web.webstart.base.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.jpa.domain.Specification;

public class DynamicSpecifications {
	private static final ConversionService conversionService = new DefaultConversionService();

	@SuppressWarnings("rawtypes")
	public static <T> Specification<T> bySearchFilter(
			final Collection<SearchFilter> filters, final Class<T> clazz) {
		return new Specification<T>() {
			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
					CriteriaBuilder builder) {
				if (XaUtil.isNotEmpty(filters)) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					for (SearchFilter filter : filters) {

						String[] names = filter.fieldName.split("\\.");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}

						// convert value from string to target type
						Class attributeClass = expression.getJavaType();
						if (!attributeClass.equals(String.class)
								&& filter.value instanceof String
								&& conversionService.canConvert(String.class,
										attributeClass)) {
							filter.value = conversionService.convert(
									filter.value, attributeClass);
						}

						// logic operator
						switch (filter.operator) {
						case EQ:
							predicates.add(builder.equal(expression,
									filter.value));
							break;
						case NE:
							predicates.add(builder.notEqual(expression,
									filter.value));
							break;
						case IN:
							predicates.add(expression.in(((List) filter.value)
									.toArray()));
							break;
						case ISNULL:
							predicates.add(builder.isNull(expression));
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%"
									+ filter.value + "%"));
							
//							System.out.println("%"+ filter.value + "%");
							break;
						case LIKEIGNORE:
							predicates.add(builder.like(builder.lower(expression), "%"
									+ filter.value.toString().toLowerCase() + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression,
									(Comparable) filter.value));
							break;
						case LT:
							predicates.add(builder.lessThan(expression,
									(Comparable) filter.value));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(
									expression, (Comparable) filter.value));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(
									expression, (Comparable) filter.value));
							break;
						case BETWEEN:
							predicates.add(builder.between(expression, 
									(Comparable)filter.value.toString().split(",")[0], (Comparable)filter.value.toString().split(",")[1]));
							break;
//						case LIKES:
//							Predicate p = null;
//							String[] subNames2 = filter.fieldName.split("\\.");
//							for(int i=0;i<subNames2.length;i++) {
//								Path expression2 = root.get(subNames2[i]);
//								p = builder.or(p, builder.like(expression2, "%" + filter.value + "%"));
//							}
//							predicates.add(builder.and(p));
//							break;
						}
					}

					// 将所有条件用 and 联合起来
					if (predicates.size() > 0) {
						return builder.and(predicates
								.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}
}
