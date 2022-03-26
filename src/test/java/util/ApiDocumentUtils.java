package util;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

public interface ApiDocumentUtils {

	static OperationRequestPreprocessor getDocumentRequest() {
		return preprocessRequest(
			modifyUris()
				.scheme("http")
				.host("localhost")
				.port(8080),
				// .removePort(),
			prettyPrint());
	}

	static OperationResponsePreprocessor getDocumentResponse() {
		return preprocessResponse(prettyPrint());
	}
}
