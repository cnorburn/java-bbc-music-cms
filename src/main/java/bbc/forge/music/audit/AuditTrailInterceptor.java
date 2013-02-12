package bbc.forge.music.audit;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;


public class AuditTrailInterceptor extends EmptyInterceptor {

	protected final Log logger = LogFactory.getLog(getClass());


	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		logger.info("############  AuditTrailInterceptor onSave " + state.toString() );

		if (entity instanceof Auditable)
		{
			
	         for ( int i=0; i<propertyNames.length; i++ ) {
	                if ( "name".equals( propertyNames[i] ) ) {
	                	logger.info("############  AuditTrailInterceptor state " + state[i].toString() );
	                    //state[i] = new Date();
	                    return true;
	                }
	         }

			//AuditTrail auditTrail = getAuditTrail(state);
			//auditTrail.setName("username-xxx");
						
			
			return true;
		}
		return false;		
	}
	

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState,Object[] previousState, String[] propertyNames, Type[] types)	{
		
		logger.info("############  AuditTrailInterceptor onFlushDirty " + entity.toString() );

		if (entity instanceof Auditable)
		{
			AuditTrail auditTrail = getAuditTrail(currentState);
			return true;
		}

		return false;
	}
	
			
	private AuditTrail getAuditTrail(Object[] state)
	{
		AuditTrail auditTrail = null;
		logger.info("############  AuditTrailInterceptor getAuditTrail 1 ");
		for (Object o : state)
		{
			logger.info("############  AuditTrailInterceptor getAuditTrail o.toString - " + o.toString() + ", o - " + o);
			if (o instanceof AuditTrail){
				logger.info("############  AuditTrailInterceptor getAuditTrail 2 ");
				auditTrail = (AuditTrail) o; 
			}
		}
		return auditTrail;
	}
	
	
}

