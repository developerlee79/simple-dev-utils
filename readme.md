# Simple Dev Utilities

---

<br>

> Upload seemingly trivial utilities that may appear useless at first glance but could prove to be unexpectedly necessary or valuable

<br>

TOC

[.properties -> .yaml format converter](#properties---yaml-format-converter)

<br>

### .properties -> .yaml format converter

Convert .properties file format to .yaml file format.

```kotlin
class Test {

    @Test
    fun convertPropertiesToYaml() {
        // given
        val propertyString = "kotlin.code.style=official"
        // when
        val yamlConverter = YamlConverter()
        val yamlString = yamlConverter.convertToYaml(propertyString)
        // then
        require("kotlin: \n   code: \n      style: official\n\n" == yamlString)
    }
    
}
```
