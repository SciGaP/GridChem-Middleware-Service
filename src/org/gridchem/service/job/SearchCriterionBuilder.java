/**
 * 
 */
package org.gridchem.service.job;

import static org.gridchem.service.model.enumeration.JobSearchParameterTypes.APPLICATION;
import static org.gridchem.service.model.enumeration.JobSearchParameterTypes.CREATED;
import static org.gridchem.service.model.enumeration.JobSearchParameterTypes.ID;
import static org.gridchem.service.model.enumeration.JobSearchParameterTypes.START_TIME;
import static org.gridchem.service.model.enumeration.JobSearchParameterTypes.STATUS;
import static org.gridchem.service.model.enumeration.JobSearchParameterTypes.STOP_TIME;
import static org.gridchem.service.model.enumeration.JobSearchParameterTypes.SUBMIT_MACHINE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gridchem.service.beans.SearchBean;
import org.gridchem.service.dao.SearchDao;
import org.gridchem.service.dao.SoftwareDao;
import org.gridchem.service.exceptions.JobSearchException;
import org.gridchem.service.model.ComputeResource;
import org.gridchem.service.model.Software;
import org.gridchem.service.model.enumeration.JobSearchFilterType;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

/**
 * Worker class to translate SearchBean.SearchParameters into Hibernate Criterion objects
 * @author dooley
 *
 */
public class SearchCriterionBuilder {
	
	public static Criterion build(String field, SearchBean.SearchParameter parameter) {
		Criterion exp = null;
        
		String[] parsedTerms;
        
        try {
            if (field.equals(ID)) { 
                // job id is a Long, so we convert each term after parsing
                String dummy = "";
                parsedTerms = parseSearchTerm(parameter.value);
                Long[] longTerms = new Long[parsedTerms.length];
                if (parsedTerms.length > 1) {
                    for(int i=0;i<parsedTerms.length;i++) {
                        dummy += parsedTerms[i] + ":::";
                        longTerms[i] = new Long(parsedTerms[i]);
                    }
                    exp = resolveRestrictionsByType(parameter.type,field,longTerms);
                } else {
                    dummy = parameter.value;
                    exp = resolveRestrictionsByType(parameter.type,field,new Long(parameter.value));
                }
//                log.debug("Parsed search string into " + dummy );
//                
            } else if (field.equals(CREATED) || field.equals(START_TIME) || field.equals(STOP_TIME)) {
                // date strings are parsed before the expression is created.
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(parameter.value);
//                    log.debug("Parsed date search string into " + date.toString());
                    exp = resolveRestrictionsByType(parameter.type,field,date);
                } catch (ParseException e) {
                    throw new JobSearchException("Could not parse requested CPU time: " + parameter.value);
                }
            } else if (field.equals(STATUS)) {
                String dummy = "";
                parsedTerms = parseSearchTerm(parameter.value);
                JobStatusType[] statusTerms = new JobStatusType[parsedTerms.length];
                if (parsedTerms.length > 1) {
                    for(int i=0;i<parsedTerms.length;i++) {
                        JobStatusType status = resolveStatus(parsedTerms[i]);
                        statusTerms[i] = status;
                        dummy += status.name() + ":::";
                    }
                    exp = resolveRestrictionsByType(parameter.type,field,statusTerms);
                } else {
                    JobStatusType status = resolveStatus(parameter.value);
                    dummy = status.name();
                    exp = resolveRestrictionsByType(parameter.type,field,status);
                }
//                log.debug("Parsed search string into " + dummy );
                for(int i=0;i<parsedTerms.length;i++) {
                    JobStatusType status = resolveStatus(parsedTerms[i]);
                    exp = resolveRestrictionsByType(parameter.type,field,status);
                }
            } else if (field.equals(SUBMIT_MACHINE)) { 
                //we must look up the machine name first
                // if we don't find it, we do a dummy search 
                // so that the query returns no results rather than
                // an exception.
                String dummy = "";
                parsedTerms = parseSearchTerm(parameter.value);
                
                ArrayList<Long> lookups;
                if (parsedTerms.length > 1) {
                    lookups = new ArrayList<Long>();
                    for(int i=0;i<parsedTerms.length;i++) {
                        dummy += parsedTerms[i] + ":::";
                        List<ComputeResource> resources = SearchDao._getComputeResources(parsedTerms[i], parameter.type);
                        if (resources.size() == 0) {
                            lookups.add(new Long(0));
                        } else {
                            for(ComputeResource hpc: resources) {
                                lookups.add(hpc.getId());
                            }
                        }
                    }
                    resolveRestrictionsByType(parameter.type,field,(Long[])lookups.toArray());
                } else {
                    lookups = new ArrayList<Long>();
                    List<ComputeResource> resources = SearchDao._getComputeResources(parameter.value, parameter.type);
                    if (resources == null ||resources.size() == 0) {
                        lookups.add(new Long(0));
                    } else {
                        for(ComputeResource hpc: resources) {
                            lookups.add(hpc.getId());
                        }
                    }
                    
                    if (lookups.size() > 1) {
                        exp = resolveRestrictionsByType(parameter.type,field,(Long[])lookups.toArray());
                    } else {
                        exp = resolveRestrictionsByType(parameter.type,field,(Long)lookups.get(0));
                    }
                    
                }
                    
//                log.debug("Parsed search string into " + dummy );
                for(int i=0;i<parsedTerms.length;i++) {
                    
                }
            } else if (field.equals(APPLICATION)) { 
                // we must look up the application name first
                // if we don't find it, we do a dummy search 
                // so that the query returns no results rather than
                // an exception.
                String dummy = "";
                SoftwareDao swDao = new SoftwareDao();
                parsedTerms = parseSearchTerm(parameter.value);
                
                ArrayList<Long> lookups;
                if (parsedTerms.length > 1) {
                    lookups = new ArrayList<Long>();
                    for(int i=0;i<parsedTerms.length;i++) {
                        dummy += parsedTerms[i] + ":::";
                        List<Software> resources = SearchDao._getSoftware(parsedTerms[i], parameter.type);
                        if (resources == null || resources.size() == 0) {
                            lookups.add(new Long(0));
                        } else {
                            for(Software sw: resources) {
                                lookups.add(sw.getId());
                            }
                        }
                    }
                    resolveRestrictionsByType(parameter.type,field,(Long[])lookups.toArray());
                } else {
                    lookups = new ArrayList<Long>();
                    List<Software> resources = SearchDao._getSoftware(parameter.value, parameter.type);
                    if (resources.size() == 0) {
                        lookups.add(new Long(0));
                    } else {
                        for(Software sw: resources) {
                            lookups.add(sw.getId());
                        }
                    }
                    
                    if (lookups.size() > 1) {
                        exp = resolveRestrictionsByType(parameter.type,field,(Long[])lookups.toArray());
                    } else {
                        exp = resolveRestrictionsByType(parameter.type,field,(Long)lookups.get(0));
                    }
                    
                }
                    
//                log.debug("Parsed search string into " + dummy );
                
//                SoftwareDao swDao = new SoftwareDao();
//                String dummy = "";
//                
//                parsedTerms = parseSearchTerm(parameter.value);
//                for(int i=0;i<parsedTerms.length;i++) {
//                    dummy += parsedTerms[i] + ":::";
//                }
//                
//                System.out.println("Parsed search string into " + dummy );
//                for(int i=0;i<parsedTerms.length;i++) {
//                    List<Software> resources = swDao._get(parsedTerms[i], parameter.type);
//                    if (resources.size() == 0) {
//                        expressions.add(Restrictions.eq(field, new Long(0)));
//                    } else {
//                        for(Software sw: resources) {
//                            exp = resolveRestrictionsByType(parameter.type,field,sw.getId());
//                            
//                            expressions.add(exp);
//                        }
//                    }
//                }
            } else { // all other job fields are strings
                String dummy = "";
                parsedTerms = parseSearchTerm(parameter.value);
                if (parsedTerms.length > 1) {
                    for(int i=0;i<parsedTerms.length;i++) {
                        dummy += parsedTerms[i] + ":::";
                    }
                    exp = resolveRestrictionsByType(parameter.type,field,parsedTerms);
                } else {
                    dummy = parameter.value;
                    exp = resolveRestrictionsByType(parameter.type,field,parameter.value);
                }
//                log.debug("Parsed search string into " + dummy );
//                for(int i=0;i<parsedTerms.length;i++) {
//                    dummy += parsedTerms[i] + ":::";
//                }
//                System.out.println("Parsed search string into " + dummy );
//                for(int i=0;i<parsedTerms.length;i++) {
//                    exp = resolveRestrictionsByType(parameter.type,field,parsedTerms[i]);
//                    expressions.add(exp);
//                }
            }
        } catch (JobSearchException e) {
            e.printStackTrace();
            throw e;
        } catch(Exception e) {
            e.printStackTrace();
            throw new JobSearchException("Error creating " + field + " search criteria.");
        }
        return exp;
    }
    
    private static JobStatusType resolveStatus(String s) {
        JobStatusType retval = null;
        for (JobStatusType status: JobStatusType.values()) {
            if (status.name().equals(s.toUpperCase()))
                retval = status;
        }
        return retval;
    }
    
    /**
     * Split the string by non-alphanumeric characters.
     * 
     * @param searchTerm
     * @return
     */
    private static String[] parseSearchTerm(String searchTerm) {
        return searchTerm.split("[\\:\\;\\,]");
    }
    
    private static SimpleExpression resolveRestrictionsByType(JobSearchFilterType filter, String field, Date value) {
        SimpleExpression exp = null;
        //BEFORE, AFTER, EQ, GT, GEQ, LT, LEQ, NEQ, LIKE, NOT_LIKE, PREFIX, SUFFIX
        if (filter.name().equals(JobSearchFilterType.EQ.name())) {
            exp =  Restrictions.eq(field,value);
        } else if (filter.name().equals(JobSearchFilterType.NEQ.name())) {
            exp = Restrictions.ne(field,value);
        } else if (filter.name().equals(JobSearchFilterType.AFTER.name())) {
            exp = Restrictions.ge(field,value);
        } else if (filter.name().equals(JobSearchFilterType.BEFORE.name())) {
            exp = Restrictions.le(field,value);
        } else if (filter.name().equals(JobSearchFilterType.LT.name())) {
            exp = Restrictions.lt(field,value);
        } else if (filter.name().equals(JobSearchFilterType.LEQ.name())) {
            exp = Restrictions.le(field,value);
        } else if (filter.name().equals(JobSearchFilterType.GT.name())) {
            exp = Restrictions.gt(field,value);
        } else if (filter.name().equals(JobSearchFilterType.GEQ.name())) {
            exp = Restrictions.ge(field,value);
        } else {
            throw new JobSearchException(filter + 
                    " comparison operator not supported for the " + 
                    field + " job attribute.");
        }
        
        return exp;
    }
    
    private static Criterion resolveRestrictionsByType(JobSearchFilterType filter, String field, String value) {
        Criterion exp = Restrictions.like("","");
        //BEFORE, AFTER, EQ, GT, GEQ, LT, LEQ, NEQ, LIKE, NOT_LIKE, PREFIX, SUFFIX
        if (filter.name().equals(JobSearchFilterType.EQ.name())) {
            exp =  Restrictions.eq(field,value);
        } else if (filter.name().equals(JobSearchFilterType.NEQ.name())) {
            exp = Restrictions.ne(field,value);
        } else if (filter.name().equals(JobSearchFilterType.LIKE.name())) {
            exp = Restrictions.like(field,value, MatchMode.ANYWHERE);
        } else if (filter.name().equals(JobSearchFilterType.NOT_LIKE.name())) {
            exp = Restrictions.not(Restrictions.like(field,value,MatchMode.ANYWHERE));
        } else if (filter.name().equals(JobSearchFilterType.PREFIX.name())) {
            exp = Restrictions.like(field,value.toString(),MatchMode.START);
        } else if (filter.name().equals(JobSearchFilterType.SUFFIX.name())) {
            exp = Restrictions.like(field,value.toString(),MatchMode.END);
        } else {
            throw new JobSearchException(filter + 
                    " comparison operator not supported for the " + 
                    field + " job attribute.");
        }
        
        return exp;
    }
    
    private SimpleExpression resolveRestrictionsByType(JobSearchFilterType filter, String field, int value) {
        SimpleExpression exp = Restrictions.like("","");
        //BEFORE, AFTER, EQ, GT, GEQ, LT, LEQ, NEQ, LIKE, NOT_LIKE, PREFIX, SUFFIX
        if (filter.name().equals(JobSearchFilterType.EQ.name())) {
            exp =  Restrictions.eq(field,value);
        } else if (filter.name().equals(JobSearchFilterType.NEQ.name())) {
            exp = Restrictions.ne(field,value);
        } else if (filter.name().equals(JobSearchFilterType.LT.name())) {
            exp = Restrictions.lt(field,value);
        } else if (filter.name().equals(JobSearchFilterType.LEQ.name())) {
            exp = Restrictions.le(field,value);
        } else if (filter.name().equals(JobSearchFilterType.GT.name())) {
            exp = Restrictions.gt(field,value);
        } else if (filter.name().equals(JobSearchFilterType.GEQ.name())) {
            exp = Restrictions.ge(field,value);
        } else {
            throw new JobSearchException(filter + 
                    " comparison operator not supported for the " + 
                    field + " job attribute.");
        }
        return exp;
    }
    
    private static SimpleExpression resolveRestrictionsByType(JobSearchFilterType filter, String field, Long value) {
        SimpleExpression exp = Restrictions.like("","");
        //BEFORE, AFTER, EQ, GT, GEQ, LT, LEQ, NEQ, LIKE, NOT_LIKE, PREFIX, SUFFIX
        if (filter.name().equals(JobSearchFilterType.EQ.name())) {
            exp =  Restrictions.eq(field,value);
        } else if (filter.name().equals(JobSearchFilterType.NEQ.name())) {
            exp = Restrictions.ne(field,value);
        } else if (filter.name().equals(JobSearchFilterType.LT.name())) {
            exp = Restrictions.lt(field,value);
        } else if (filter.name().equals(JobSearchFilterType.LEQ.name())) {
            exp = Restrictions.le(field,value);
        } else if (filter.name().equals(JobSearchFilterType.GT.name())) {
            exp = Restrictions.gt(field,value);
        } else if (filter.name().equals(JobSearchFilterType.GEQ.name())) {
            exp = Restrictions.ge(field,value);
        } else if (filter.name().equals(JobSearchFilterType.LIKE.name())) {
            exp = Restrictions.like(field,value);
        } else if (filter.name().equals(JobSearchFilterType.PREFIX.name())) {
            exp = Restrictions.like(field,value);
        } else if (filter.name().equals(JobSearchFilterType.SUFFIX.name())) {
            exp = Restrictions.like(field,value);
        } else {
            throw new JobSearchException(filter + 
                    " comparison operator not supported for the " + 
                    field + " job attribute.");
        }
        return exp;
    }
    
    private static SimpleExpression resolveRestrictionsByType(JobSearchFilterType filter, String field, JobStatusType value) {
    	SimpleExpression exp = Restrictions.like("","");
        //BEFORE, AFTER, EQ, GT, GEQ, LT, LEQ, NEQ, LIKE, NOT_LIKE, PREFIX, SUFFIX
        if (filter.name().equals(JobSearchFilterType.EQ.name())) {
            exp =  Restrictions.eq(field,value);
        } else if (filter.name().equals(JobSearchFilterType.NEQ.name())) {
            exp = Restrictions.ne(field,value);
        } else {
            throw new JobSearchException(filter + 
                    " comparison operator not supported for the " + 
                    field + " job attribute.");
        }
        return exp;
    }
    
    private static Criterion resolveRestrictionsByType(JobSearchFilterType filter, String field, Long[] values) {
        return Restrictions.in(field,values);
    }
    
    private static Criterion resolveRestrictionsByType(JobSearchFilterType filter, String field, String[] values) {
        return Restrictions.in(field,values);
    }
    
    private static Criterion resolveRestrictionsByType(JobSearchFilterType filter, String field, JobStatusType[] values) {
        return Restrictions.in(field,values);
    }

}
