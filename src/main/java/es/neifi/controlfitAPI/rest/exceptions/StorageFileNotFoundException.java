package es.neifi.controlfitAPI.rest.exceptions;

import es.neifi.controlfitAPI.rest.exceptions.StorageException;

public class StorageFileNotFoundException extends StorageException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8482217129851689197L;

	public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}