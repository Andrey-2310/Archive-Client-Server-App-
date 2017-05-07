package Server.communication;

import Server.Cataloguer;
import general.user.UserRepository;
import general.Request;
import general.Response;

/**
 * The Class ServeRequest handles with requests from client.
 */
public class ServeRequest {

	/** The request. */
	private Request request;

	/**
	 * Instantiates a new serve request.
	 *
	 * @param request
	 *            the request
	 */
	public ServeRequest(Request request) {
		super();
		this.request = request;
	}

	/**
	 * Serves request, accordingly to request calls required method in
	 * cataloger.
	 *
	 * @return the response
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	
	public Response serveRequest() throws IllegalArgumentException {
		Response response = null;
		switch (request.getRequest()) {
		case SHOW_ALL:
			response = Cataloguer.getAll();
			break;

		case FIND:
			response = Cataloguer.find(request.getSearchStr());
			break;

		case ADD:
			response = Cataloguer.add(request.getPersonalData());
			break;

		case EDIT:
			response = Cataloguer.edit(request.getPersonalData(), request.getPersonalDataNew());
			break;

		case DELETE:
			response = Cataloguer.delete(request.getPersonalData());
			break;
		case SHOW_USERS:
			response = UserRepository.getAll();
			break;
		case CHANGE_USERS:
			response = UserRepository.changeUsers(request.getUsers());
			break;
		case CHANGE_PARSER:
			response=Cataloguer.changeParser(request.getSearchStr());
			break;
		default:
			throw new IllegalArgumentException();
		}
		return response;
	}
}
