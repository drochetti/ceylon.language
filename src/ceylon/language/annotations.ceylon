import ceylon.language.metamodel{SequencedAnnotation, Type}

"The annotation class for [[annotation]]."
shared annotation class Annotation() {}

"Annotation to mark a class as an annotation class, or 
 a method as an annotation method."
shared annotation Annotation annotation() => Annotation(); 

"The annotation class for [[shared]]."
shared annotation class Shared() {}

"Annotation to mark a type or member as shared. A `shared` 
 member is visible outside the block of code in which it
 is declared."
shared annotation Shared shared() => Shared();

"The annotation class for [[variable]]."
shared annotation class Variable() {}

"Annotation to mark an value as variable. A `variable` 
 value must be assigned multiple times." 
shared annotation Variable variable() => Variable();

"The annotation class for [[abstract]]."
shared annotation class Abstract() {}

"Annotation to mark a class as abstract. An `abstract` 
 class may not be directly instantiated. An `abstract`
 class may have enumerated cases."
shared annotation Abstract abstract() => Abstract();

"The annotation class for [[final]]."
shared annotation class Final() {}

"Annotation to mark a class as final. A `final` class 
 may not be extended."
shared annotation Final final() => Final();

"The annotation class for [[actual]]."
shared annotation class Actual() {}

"Annotation to mark a member of a type as refining a 
 member of a supertype."
shared annotation Actual actual() => Actual();

"The annotation class for [[formal]]."
shared annotation class Formal() {}

"Annotation to mark a member whose implementation must 
 be provided by subtypes."  
shared annotation Formal formal() => Formal();

"The annotation class for [[default]]."
shared annotation class Default() {}

"Annotation to mark a member whose implementation may be 
 refined by subtypes. Non-`default` declarations may not 
 be refined."
shared annotation Default default() => Default();

"The annotation class for [[late]]."
shared annotation class Late() {}

"Annotation to disable definite initialization analysis
 for a reference."  
shared annotation Late late() => Late();

"The annotation class for [[native]]."
shared annotation class Native() {}

"Annotation to mark a member whose implementation is 
 be provided by platform-native code."  
shared annotation Native native() => Native();

shared annotation class Doc(shared String description) {}

"Annotation to specify API documentation of a program
 element." 
shared annotation Doc doc(String description) => Doc(description);

shared annotation class See(shared Anything* programElements) satisfies SequencedAnnotation<See, Type<Anything>> {}

"Annotation to specify API references to other related 
 program elements."
shared annotation See see(Anything* programElements) => See(*programElements);

"The annotation class for [[by]]."
shared annotation class Authors(shared String* authors) {}

"Annotation to specify API authors."
shared annotation Authors by(String* authors) => Authors(*authors);

shared annotation class ThrownException(shared Anything type, shared String when) satisfies SequencedAnnotation<See, Type<Anything>> {}

"Annotation to mark a program element that throws an 
 exception."
shared annotation ThrownException throws(Anything type, String when="") => ThrownException(type, when);

"The annotation class for [[deprecated]]."
shared annotation class Deprecation(shared String description) {
    shared String? reason {
        if (description.empty) {
            return null;
        }
        return description;
    }
}

"Annotation to mark program elements which should not be 
 used anymore."
shared annotation Deprecation deprecated(String reason="") => Deprecation(reason);

"The annotation class for [[tagged]]."
shared annotation class Tags(shared String* tags) {}

"Annotation to categorize the API by tag." 
shared annotation Tags tagged(String* tags) => Tags(*tags);

"The annotation class for [[license]]."
shared annotation class License(shared String url) {}

"Annotation to specify the URL of the license of a module 
 or package." 
shared annotation License license(String url) => License(url);

"The annotation class for [[optional]]."
shared annotation class Optional() {}

"Annotation to specify that a module can be executed 
 even if the annotated dependency is not available."
shared annotation Optional optional() => Optional();
