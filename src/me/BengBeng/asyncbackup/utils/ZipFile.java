package me.BengBeng.asyncbackup.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile {
	
	public void convertToZip(Path sourceDir, Path targetFile) {
		File parent = targetFile.toFile().getParentFile();
		if((!parent.isFile()) && (!parent.exists())) {
			parent.mkdirs();
		}
		File second = targetFile.toFile();
		try {
			ZipDirectoryVisitor zipVisitor = new ZipDirectoryVisitor(sourceDir);
			Files.walkFileTree(sourceDir, zipVisitor);
			FileOutputStream fos = new FileOutputStream(second);
			ZipOutputStream zos = new ZipOutputStream(fos);
			byte[] buffer = new byte[1024];
			for(ZipEntry entry : zipVisitor.getZipEntries()) {
				zos.putNextEntry(entry);
				Path curFile = Paths.get(sourceDir.getParent().toString(), entry.toString());
				if(!curFile.toFile().isDirectory()) {
					FileInputStream in = new FileInputStream(Paths.get(sourceDir.getParent().toString(), entry.toString()).toString());
					int len;
					while((len = in.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
					in.close();
				}
				zos.closeEntry();
			}
			zos.close();
		} catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
}
