package raf.graffito.dsw.model;

public class GraffRepository {
    private Workspace rootWorkspace;

    public GraffRepository(Workspace rootWorkspace){
        this.rootWorkspace = rootWorkspace;
    }
    public Workspace getRootWorkspace(){
        return this.rootWorkspace;
    }
    public void setRootWorkspace(Workspace rootWorkspace){
        this.rootWorkspace = rootWorkspace;
    }

    public GraffNode findByName(String name){
        if(this.rootWorkspace == null){
            return null;
        }
        return this.rootWorkspace.findByName(name);
    }

    public boolean validateAllProjects(){
        if(rootWorkspace == null){
            return true;
        }
        for (GraffNode node : rootWorkspace.getChildren() ) {
            if(node instanceof Project project){
                if(!project.isValid()){
                    System.err.println(" Projekat '" + project.getIme() + "' je prazan (nema prezentacije ni slajdove).");
                    return false;
                }
            }
        }
        return true;
    }
}
