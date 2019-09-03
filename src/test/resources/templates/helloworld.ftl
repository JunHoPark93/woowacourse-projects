FreeMarker Template 예제 : ${message}

=======================
===  County List   ====
=======================
<#list countries as country>
    ${country_index + 1}. ${country}
</#list>