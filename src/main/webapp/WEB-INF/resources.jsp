<%@ taglib tagdir="/WEB-INF/tags" prefix="t"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<t:template>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Description</th>
				<th>Count</th>
			</tr>
		</thead> 
		<tbody>
			<c:forEach items="${resources}" var="resource">
				<tr>
					<td>${resource.id}</td>
					<td>${resource.title}</td>
					<td>${resource.description}</td>
					<td>${resource.count}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</t:template>