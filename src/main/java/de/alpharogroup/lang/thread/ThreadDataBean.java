package de.alpharogroup.lang.thread;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link ThreadDataBean} holds data from a Thread.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ThreadDataBean {
	private Integer priority;
	private boolean alive;
	private boolean daemon;
	private boolean interrupted;
	private String threadGroup;
	private String name;

	public static ThreadDataBean of(final Thread thread) {
		return ThreadDataBean.builder()
				.priority(thread.getPriority())
				.alive(thread.isAlive())
				.daemon(thread.isDaemon())
				.interrupted(thread.isInterrupted())
				.threadGroup(thread.getThreadGroup().getName())
				.name(thread.getName())
				.build();
	}
}
