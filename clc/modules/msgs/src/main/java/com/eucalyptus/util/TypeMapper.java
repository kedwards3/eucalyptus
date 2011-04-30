package com.eucalyptus.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.eucalyptus.bootstrap.ServiceJarDiscovery;
import com.google.common.base.Function;

/**
 * A well formed type mapper annotation is on an instance of {@link Function} and the annotation's value must be a pair of class types {@code F, T} such that
 * {@code T result = Function#apply(F)}.
 * 
 * An off-the-cuff object-to-string example to indicate the spirit of the annotations usage:
 * 
 * <pre>
 * &#064;TypeMapper( { Object.class, String.class } )
 * private class ToString implements Function&lt;Object, String&gt; {
 *   public String apply( Object o ) {
 *     return o.toString( );
 *   }
 * };
 * </pre>
 * 
 * The same mapping could also be specified as:
 * 
 * <pre>
 * &#064;TypeMapper( from=Object.class, to=String.class )
 * </pre>
 * 
 * The default {@code @TypeMapper( From.class,To.class})} will be used if both are present.
 * A TypeMapper Function is registered in {@link TypeMappers} during {@link ServiceJarDiscovery} by {@link TypeMappers.TypeMapperDiscovery}.
 */
@Target( { ElementType.TYPE, ElementType.FIELD } )
@Retention( RetentionPolicy.RUNTIME )
public @interface TypeMapper {
  Class[] value( ) default { Object.class, Object.class };
  
  Class from( ) default Object.class;
  
  Class to( ) default Object.class;
}
