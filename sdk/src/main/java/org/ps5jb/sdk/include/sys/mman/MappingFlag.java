package org.ps5jb.sdk.include.sys.mman;

import org.ps5jb.sdk.res.ErrorMessages;

/**
 * Constants for memory mapping flags.
 */
public final class MappingFlag implements Comparable {
    /** Map from file (default). */
    public static final MappingFlag MAP_FILE = new MappingFlag(0x00000, "MAP_FILE");
    /** Share changes. */
    public static final MappingFlag MAP_SHARED = new MappingFlag(0x00001, "MAP_SHARED");
    /** Changes are private. */
    public static final MappingFlag MAP_PRIVATE = new MappingFlag(0x00002, "MAP_PRIVATE");
    /** @deprecated Use {@link #MAP_PRIVATE}. */
    @Deprecated
    public static final MappingFlag MAP_COPY = new MappingFlag(MAP_PRIVATE.value(), "MAP_COPY");
    /** Map addr must be exactly as requested. */
    public static final MappingFlag MAP_FIXED = new MappingFlag(0x00010, "MAP_FIXED");
    /** Region may contain semaphores. */
    public static final MappingFlag MAP_HASSEMAPHORE = new MappingFlag(0x00200, "MAP_HASSEMAPHORE");
    /** Region grows down, like a stack. */
    public static final MappingFlag MAP_STACK = new MappingFlag(0x00400, "MAP_STACK");
    /** Page to but do not sync underlying file. */
    public static final MappingFlag MAP_NOSYNC = new MappingFlag(0x00800, "MAP_NOSYNC");
    /** Allocated from memory, swap space. */
    public static final MappingFlag MAP_ANON = new MappingFlag(0x01000, "MAP_ANON");
    /** For compatibility, same as {@link #MAP_ANON}. */
    public static final MappingFlag MAP_ANONYMOUS = new MappingFlag(MAP_ANON.value(), "MAP_ANONYMOUS");
    /** For {@link #MAP_FIXED}, fail if address is used. */
    public static final MappingFlag MAP_EXCL = new MappingFlag(0x04000, "MAP_EXCL");
    /** Do not include these pages in a coredump. */
    public static final MappingFlag MAP_NOCORE = new MappingFlag(0x20000, "MAP_NOCORE");
    /** Prefault mapping for reading. */
    public static final MappingFlag MAP_PREFAULT_READ = new MappingFlag(0x40000, "MAP_PREFAULT_READ");
    /** Map in the low 2GB of address space (kernel-mode only flag). */
    public static final MappingFlag MAP_32BIT = new MappingFlag(0x80000, "MAP_32BIT");

    /** All possible MappingFlag values. */
    private static final MappingFlag[] values = new MappingFlag[] {
            MAP_FILE,
            MAP_SHARED,
            MAP_PRIVATE,
            MAP_COPY,
            MAP_FIXED,
            MAP_HASSEMAPHORE,
            MAP_STACK,
            MAP_NOSYNC,
            MAP_ANON,
            MAP_ANONYMOUS,
            MAP_EXCL,
            MAP_NOCORE,
            MAP_PREFAULT_READ,
            MAP_32BIT
    };

    private int value;

    private String name;

    /**
     * Default constructor. This class should not be instantiated manually,
     * use provided constants instead.
     *
     * @param value Numeric value of this instance.
     * @param name String representation of the constant.
     */
    private MappingFlag(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Get all possible values for MappingFlags.
     *
     * @return Array of MappingFlag possible values.
     */
    public static MappingFlag[] values() {
        return values;
    }

    /**
     * Convert a numeric value into a MappingFlag constant.
     *
     * @param value Number to convert
     * @return MappingFlag constant corresponding to the given value.
     * @throws IllegalArgumentException If value does not correspond to any MappingFlag.
     */
    public static MappingFlag valueOf(int value) {
        for (MappingFlag openFlag : values) {
            if (value == openFlag.value()) {
                return openFlag;
            }
        }

        throw new IllegalArgumentException(ErrorMessages.getClassErrorMessage(MappingFlag.class,"invalidValue", Integer.toString(value)));
    }

    /**
     * Numeric value of this instance.
     *
     * @return Numeric value of the instance.
     */
    public int value() {
        return this.value;
    }

    /**
     * Combines an array of flags using a bitwise OR operator.
     *
     * @param flags Flags to combine.
     * @return Result of taking {@link #value()} of each flag and doing a bitwise OR operator on them.
     */
    public static int or(MappingFlag... flags) {
        int result = 0;
        for (MappingFlag flag : flags) {
            result |= flag.value;
        }
        return result;
    }

    @Override
    public int compareTo(Object o) {
        return this.value - ((MappingFlag) o).value;
    }

    @Override
    public boolean equals(Object o) {
        boolean result;
        if (o instanceof MappingFlag) {
            result = value == ((MappingFlag) o).value;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
