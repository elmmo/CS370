## On Generics and Wildcards in Java
--- 
**Generics**, like templates in C++, give methods, classes, and interfaces the flexibility to accept multiple type arguments. A method, class, or interface using generics only needs to be defined once and can be widely applied. 

### Declaration 
--- 
**Generic Variables** 
* Arrays: `E[] arr`
* Lists: `List<E> l`
---
**Generic Classes**
```
class Foo<T> {
     public void doFoo(Collection<T> items) ...
}
```
The \<T> in the class header allows the generic *T* to be used throughout the rest of the class. 

Adding more generic values to the generic declaration (e.g., `class Foo<T, G>`) allows for multiple generics to be used within the class. 

---
**Generic Methods**
```
class Foo {
    public static <T> void doFoo(Collection<T> items) ...
}
``` 
Rather than declaring the generic in the class header, this code does it in the method signature by specifying \<T> before `void`. If the \<T> wasn't there, the compiler would throw an error because it wouldn't know what the referenced \<T> parameter was. 

--- 
**Generic Methods** w/ explicit declaration
```
class Foo {
    public static void doFoo(Collection<String> items) ...
}
``` 
The code above is the same as the example above, excpet it has to pass a Collection with a type specified, making use of the generic capability of Collection rather than defining a new generic capability for the `doFoo` method. 

Note that this will not work with primitive types like ints, chars, and doubles. 

### Wildcards 
--- 
Wildcards are declared with a `?`, like so: 
```
public static void foo(List<?> list) ...
```
This looks extremely similar to standard generics, and for good reason: wildcards are a generic type. Here, I'm going to refer to them separately for ease of reference, but their general purpose is the same. The largest difference is that with wildcards, the function doesn't know what type it's receiving or returning. The following code illustrates this: 

``` 
public class Experiment {
    public static <E> void foo(final List<E> list1, final E something) {
        list1.add(something);
    }

    public static void bar(final List<?> list, final Object something) {
        list.add(something); // does not compile
    }
}
```
The code above takes a list as its first parameter and attempts to add the second parameter to that list. In the first method, this goes off without a hitch. In the second method, this code will not work no matter what the second parameter is set to. 

The example shows that wildcards are more restrictive than generics. This should make sense intuitively since the function knows less about the type it's handling when using wildcards. With these things in mind, when should we use wildcards versus generics? 

**Advantages of Generics**

* Enforce relationships between different types of parameters (such as ensuring they're the same type)
* Support multiple bounds, whereas wildcards don't (see section below)

**Advantages of Wildcards** 

* Can restrict unsafe casting by limiting the function's ability to act on the type 

### Bounds 
--- 
Both generics and wildcards can have bounds, but they react to them in different ways. 

[In progress]



**Sources**: [[1](https://stackoverflow.com/questions/34815529/making-sense-of-generic-java-method-header)] [[2](https://www.geeksforgeeks.org/generics-in-java/?ref=lbp)] [[3](https://www.baeldung.com/java-generics)] [[4](https://stackoverflow.com/questions/10943137/difference-between-generic-type-and-wildcard-type)] [[5](https://stackoverflow.com/questions/18176594/when-to-use-generic-methods-and-when-to-use-wild-card)]