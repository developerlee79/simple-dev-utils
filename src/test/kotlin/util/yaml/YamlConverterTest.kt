package util.yaml

import kotlin.test.Test

class YamlConverterTest {

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