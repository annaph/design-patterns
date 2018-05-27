package org.design.patterns.traits.linearisation

// Animal -> AnyRef -> Any
class Animal extends AnyRef

// Dog -> Animal -> AnyRef -> Any
class Dog extends Animal
