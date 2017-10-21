package de.alpharogroup.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link GenericChangedAttribute} is a bean class that encapsulated the difference of the
 * attribute from two objects.
 *
 * @param <SOURCE>
 *            the generic type of the source attribute
 * @param <CHANGED>
 *            the generic type of the changed attribute
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
public class GenericChangedAttribute<SOURCE, CHANGED>
{
	/** The attribute name. */
	private String attributeName;

	/** The source attribute. */
	private SOURCE sourceAttribute;

	/** The changed attribute. */
	private CHANGED changedAttribute;
}
