# Test
Бала использованна h2 in memory database и flyway для миграции дб. 

 
package com.moysklad.testtask;

import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {


public static void main(String[] args) throws NoSuchFieldException {
//
// ModelMapper modelMapper = new ModelMapper();
// modelMapper.createTypeMap(EntityA.class, EntityB.class)
// .addMappings(mapper -> mapper.map(src -> {
// String digits = src.getDigits();
// return digits != null ? digits.split(",")[0] : null;
// }, EntityB::setField1))
// .addMappings(mapper -> mapper.map(src -> {
// String digits = src.getDigits();
// return digits != null ? digits.split(",")[1] : null;
// }, EntityB::setField2))
// .addMappings(mapper -> mapper.map(src -> {
// String digits = src.getDigits();
// return digits != null ? digits.split(",")[2] : null;
// }, EntityB::setField3));
//
// EntityA entityA = new EntityA();
// entityA.setDigits("1,2,3");
//
// EntityB entityB = modelMapper.map(entityA, EntityB.class);
//
// System.out.println("Mapped value for field1: " + entityB.getField1());
// System.out.println("Mapped value for field2: " + entityB.getField2());
// System.out.println("Mapped value for field3: " + entityB.getField3());
// System.out.println("Concatenated values: " + entityB.getField1() + "," + entityB.getField2() + "," + entityB.getField3());
//
// String xxx = "1,2,3,4";
// System.out.println(Arrays.toString(xxx.split(",")));



// ModelMapper modelMapper = new ModelMapper();
//
// Converter<String, String> digitsConverter = ctx -> {
// String digits = ctx.getSource();
// if (digits != null) {
// String[] parts = digits.split(",");
// if (parts.length >= 3) {
// return parts[ctx.getMapping().getDestinationIndex() - 1];
// }
// }
// return null;
// };
//
// modelMapper.typeMap(EntityA.class, EntityB.class)
// .addMappings(mapper -> {
// mapper.map(EntityA::getDigits, EntityB::setField1);
// mapper.map(EntityA::getDigits, EntityB::setField2);
// mapper.map(EntityA::getDigits, EntityB::setField3);
// })
// .setPropertyCondition(Conditions.isNotNull())
// .setPropertyConverter(digitsConverter);
//
// EntityA entityA = new EntityA();
// entityA.setDigits("1,2,3");
//
// EntityB entityB = modelMapper.map(entityA, EntityB.class);
//
// System.out.println("Mapped value for field1: " + entityB.getField1());
// System.out.println("Mapped value for field2: " + entityB.getField2());
// System.out.println("Mapped value for field3: " + entityB.getField3());
// System.out.println("Concatenated values: " + entityB.getField1() + ","
// + entityB.getField2() + "," + entityB.getField3());

ModelMapper modelMapper = new ModelMapper();

Converter<String, String> digitsConverter = ctx -> {
String digits = ctx.getSource();
if (digits != null) {
String[] parts = digits.split(",");
if (parts.length >= 3) {
if (ctx.getDestination().equals("field1")) {
return parts[0];
} else if (ctx.getDestination().equals("field2")) {
return parts[1];
} else if (ctx.getDestination().equals("field3")) {
return parts[2];
}
}
}
return null;
};

modelMapper.typeMap(EntityA.class, EntityB.class)
.addMappings(mapper -> {
mapper.using(digitsConverter).map(EntityA::getDigits, EntityB::setField1);
mapper.using(digitsConverter).map(EntityA::getDigits, EntityB::setField2);
mapper.using(digitsConverter).map(EntityA::getDigits, EntityB::setField3);
});

EntityA entityA = new EntityA();
entityA.setDigits("1,2,3");

EntityB entityB = modelMapper.map(entityA, EntityB.class);

System.out.println("Mapped value for field1: " + entityB.getField1());
System.out.println("Mapped value for field2: " + entityB.getField2());
System.out.println("Mapped value for field3: " + entityB.getField3());
System.out.println("Concatenated values: " + entityB.getField1() + "," + entityB.getField2() + "," + entityB.getField3());

// ModelMapper modelMapper = new ModelMapper();
//
// Converter<String, String> digitsConverter = ctx -> {
// String digits = ctx.getSource();
// if (digits != null) {
// String[] parts = digits.split(",");
// if (parts.length >= 3) {
// if (ctx.getDestination().equals("digits1")) {
// return parts[0];
// } else if (ctx.getDestination().equals("digits2")) {
// return parts[1];
// } else if (ctx.getDestination().equals("digits3")) {
// return parts[2];
// }
// }
// }
// return null;
// };
//
// PropertyMap<EntityA, EntityB> propertyMap = new PropertyMap<EntityA, EntityB>() {
// @Override
// protected void configure() {
// using(digitsConverter).map(source.getDigits(), destination::setField1);
// using(digitsConverter).map(source.getDigits(), destination::setField2);
// using(digitsConverter).map(source.getDigits(), destination::setField3);
// }
// };
// modelMapper.addMappings(propertyMap);
//
// EntityA entityA = new EntityA();
// entityA.setDigits("1,2,3");
//
// EntityB entityB = modelMapper.map(entityA, EntityB.class);
//
// System.out.println("Mapped value for field1: " + entityB.getField1());
// System.out.println("Mapped value for field2: " + entityB.getField2());
// System.out.println("Mapped value for field3: " + entityB.getField3());
// System.out.println("Concatenated values: " + entityB.getField1() + "," + entityB.getField2() + "," + entityB.getField3());
//

}
}






 
package com.moysklad.testtask;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Arrays;
import java.util.List;

public class Main2 {

public static void main(String[] args) {


ModelMapper modelMapper = new ModelMapper();

Converter<String, List<String>> stringToListConverter = ctx -> {
String input = ctx.getSource();
if (input != null) {
return Arrays.asList(input.split(","));
}
return null;
};

PostConverter<EntityA, EntityB> digitsConverter = (ctx) -> {
EntityA source = ctx.getSource();
EntityB destination = ctx.getDestination();
List<String> digits = stringToListConverter.convert(source.getDigits());
if (digits != null && digits.size() >= 3) {
destination.setField1(digits.get(0));
destination.setField2(digits.get(1));
destination.setField3(digits.get(2));
}
};

TypeMap<EntityA, EntityB> typeMap = modelMapper.createTypeMap(EntityA.class, EntityB.class);
typeMap.addMappings(mapper -> mapper.using(digitsConverter).skip(EntityB::setField1).skip(EntityB::setField2).skip(EntityB::setField3));

EntityA entityA = new EntityA();
entityA.setDigits("1,2,3");

EntityB entityB = modelMapper.map(entityA, EntityB.class);

System.out.println("Mapped value for field1: " + entityB.getField1());
System.out.println("Mapped value for field2: " + entityB.getField2());
System.out.println("Mapped value for field3: " + entityB.getField3());
System.out.println("Concatenated values: " + entityB.getField1() + "," + entityB.getField2() + "," + entityB.getField3());



}

