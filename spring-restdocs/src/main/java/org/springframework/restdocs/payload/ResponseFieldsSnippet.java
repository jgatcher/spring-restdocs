/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.restdocs.payload;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.web.servlet.MvcResult;

/**
 * A {@link Snippet} that documents the fields in a response.
 * 
 * @author Andy Wilkinson
 */
class ResponseFieldsSnippet extends AbstractFieldsSnippet {

	ResponseFieldsSnippet(List<FieldDescriptor> descriptors) {
		this(null, descriptors);
	}

	ResponseFieldsSnippet(Map<String, Object> attributes,
			List<FieldDescriptor> descriptors) {
		super("response", attributes, descriptors);
	}

	@Override
	protected Reader getPayloadReader(MvcResult result) throws IOException {
		return new StringReader(result.getResponse().getContentAsString());
	}

	@Override
	protected MediaType getContentType(MvcResult result) {
		String contentType = result.getResponse().getContentType();
		if (contentType != null) {
			return MediaType.valueOf(contentType);
		}
		return null;
	}

}
