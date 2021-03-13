package snowball.mod;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModInfo {
	String id();
	
	String modName();
	
	String author();
	
	String description();
}
