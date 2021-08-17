<#import "parts/common.ftlh" as c>

<@c.page>
    List of verses

    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Author</th>
            <#--<th>Verse</th>-->
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list verses as verse>
            <tr>
                <td>${verse.name}</td>
                <td>${verse.author}</td>

       <#--         <#list lines as line>
                    <td>${line.text}</td>
                </#list>-->

                <#--<td><a href="/verses/${verse.id}">edit</a></td>-->
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>