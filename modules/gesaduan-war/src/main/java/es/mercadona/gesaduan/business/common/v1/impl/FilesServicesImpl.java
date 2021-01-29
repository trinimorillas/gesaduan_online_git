package es.mercadona.gesaduan.business.common.v1.impl;


import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;
import org.apache.commons.io.FileUtils;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.batch.BatchService;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.fwk.core.io.CustomFileAttributes;
import es.mercadona.fwk.core.io.FileSystemItem;
import es.mercadona.fwk.core.io.FileSystemService;
import es.mercadona.gesaduan.business.common.v1.FilesService;
import es.mercadona.fwk.core.jaxrs.ResponseFormat;
import es.mercadona.fwk.core.jaxrs.ResponseTypeInfo;


public class FilesServicesImpl implements FilesService{

	@Inject
	private BatchService batchService;
	        
	@Inject
	private FileSystemService fileSystemService;
		
	@Inject
	private org.slf4j.Logger logger;
	  
	@Inject
	private SecurityService securityService;
	
	private final boolean isPosixFileSystem = FileSystems.getDefault().supportedFileAttributeViews().contains("posix");

	@Override
	public boolean createFile(String name, boolean tieneContenido, List<String> contenido) {
			
		try {
			Map<String, FileSystemItem> ficheros = new HashMap<String, FileSystemItem>();

			final CustomFileAttributes tempFileInfo = new CustomFileAttributes();
			tempFileInfo.setUuid(UUID.randomUUID().toString());
			tempFileInfo.setContentType(ResponseTypeInfo.get(ResponseFormat.Text).mimeType);
			tempFileInfo.setFileName(name);
				
			
			final FileSystemItem fsi = fileSystemService.createTemporary(tempFileInfo);

			if (tieneContenido) {
				FileUtils.writeLines(fsi.getFile(), contenido, tieneContenido);
			}
			
			if(isPosixFileSystem) {
				setPOSIXFilePermissions(fsi.getPath().toString());
			}else {
				fsi.getFile().setReadable(true);
			}
			
			
			ficheros.put(name, fsi);
			
			/* Se envia el fichero a la ruta opt/oraas/appweb/contenido/ftp */
			batchService.launch("gesaduan", ficheros);

		} catch (IOException e) {
			
			establecerSalidaError(e, "createFile", "error.IOException");
			
			return false;
			
		}

		return true;
	}
	
	private void setPOSIXFilePermissions(String filePath) throws IOException {
		
		
        Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
        
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        /* perms.add(PosixFilePermission.OWNER_EXECUTE); */
        perms.add(PosixFilePermission.GROUP_READ);     
        perms.add(PosixFilePermission.OTHERS_READ);
        
        try {
            Files.setPosixFilePermissions(Paths.get(filePath), perms);
        } catch (IOException e) {
            
        }
    }
	
	
	  private void establecerSalidaError(Exception exception, String metodo, String codError) {

		    String login = this.securityService.getPrincipal().getLogin();
		    
		    this.logger.error("Error ejecutando la clase: FilesServicesImpl",
		        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
	  }
}
	

