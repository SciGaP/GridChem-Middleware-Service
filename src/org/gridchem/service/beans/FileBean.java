package org.gridchem.service.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileBean {
	private String name = "";   // name of file
    private String path = "";   // absolute path of file
    private String owner = "";
    private long length = 0;    // file size in bytes
    private Date lastModified; // last time the file was touched on the remote machine
    private boolean directory = false;  // is this a directory
    private String parent = null;  // uri of the parent folder
//    private List<FileBean> children = new ArrayList<FileBean>(); // directory contents, null if not a directory
    private boolean readable = false;
    private boolean writable = false;
    
    public FileBean() {}
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getUri() {
//		return uri;
//	}
//
//	public void setUri(String uri) {
//		this.uri = uri;
//	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public boolean isDirectory() {
		return directory;
	}

	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

//	public List<FileBean> getChildren() {
//		return children;
//	}
//
//	public void setChildren(List<FileBean> children) {
//		this.children = children;
//	}

	public void setReadable(boolean readable) {
		this.readable = readable;
	}

	public boolean isReadable() {
		return readable;
	}

	public void setWritable(boolean writable) {
		this.writable = writable;
	}

	public boolean isWritable() {
		return writable;
	}

	public String toString() {
        String lineItem = "";
        
        try {
        
        	lineItem =  ((isDirectory())?"d ":"- ") + "\t";
            lineItem += ((isReadable())?"r ":"- ") + ((isWritable())?"w ":"- ") + "\t";
            lineItem += getOwner() + "\t";
            lineItem += getLastModified() + "\t";
            lineItem += getLength() + "\t";
            lineItem += getName();
            
        } catch (Exception e) {
            
        	e.printStackTrace();
        }
        
        return lineItem;
    }
    
//	/**
//     * Give similar output to the unix command ls -l
//     * 
//     * @return listing a unix formatted directory listing.
//     */
//    public String list() {
//        
//    	String listing = "";
//        
//        if (directory) {
//
//            listing = "total " + children.size() + "\n" + 
//                    toString() + "\n";
//            
//            for (FileBean child: children) {
//            	
//                listing += child.toString() + "\n";
//            }
//            
//        } else {
//            
//        	listing = this.toString();
//        }
//        
//        return listing;
//    }
    
    public boolean equals(Object o) {
    	if (o instanceof FileBean) {
    		return (((FileBean)o).name.equals(name) && 
    				((FileBean)o).path.equals(path) && 
    				(((FileBean)o).length == length) &&
    				(((FileBean)o).directory == directory));
    	} else {
    		return false;
    	}
    }

}
