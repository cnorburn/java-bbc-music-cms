package bbc.forge.music.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import bbc.forge.music.dao.BrandsDao;
import bbc.forge.music.dao.ClipsDao;
import bbc.forge.music.dao.CollectionsDao;
import bbc.forge.music.dao.PlaylistsDao;
import bbc.forge.music.dao.TracksDao;
import bbc.forge.music.dao.UrlKeysDao;
import bbc.forge.music.dao.UsersDao;

import bbc.forge.music.model.Brands;
import bbc.forge.music.model.BrandsDisplay;
import bbc.forge.music.model.BrandsUsers;
import bbc.forge.music.model.CollectionUpdated;
import bbc.forge.music.model.PlaylistUpdated;
import bbc.forge.music.model.UserBrands;
import bbc.forge.music.model.Users;
import bbc.forge.music.utils.DateUtils;
import bbc.forge.music.utils.KeyGenerator;
import bbc.forge.music.utils.Logging;

public class BaseDao {
	
	protected static ApplicationContext ctx = null;

	protected Logging log = new Logging();
	protected DateUtils dateUtils = new DateUtils();
	
	protected UsersDao usersDao;
	protected BrandsDao brandsDao;
	protected PlaylistsDao playlistsDao;
	protected TracksDao tracksDao;
	protected CollectionsDao collectionsDao;
	protected ClipsDao clipsDao;
	protected UrlKeysDao urlKeysDao;
	
	static KeyGenerator keygen = new KeyGenerator();

	static Users featured;
	static Users guides;

	protected CollectionUpdated collectionUpdated;
	protected PlaylistUpdated playlistUpdated;

	protected BrandsDisplay brandsDisplay;
	protected UserBrands brands;
	protected BrandsUsers brandsUsers;

	static String playlistKey;
	static String track1Key;
	static String track2Key;
	static String collectionKey;

	static Brands brand = new Brands();

	static String FEATURED="testXYZ_featured";
	static String FEATURED_UPDATED="testXYZ_featured_updated";
	static String GUIDE="testXYZ_guide";


	public void main(){
		
		featured = new Users();

		featured.setName("test name");
		featured.setUsername(FEATURED);
		featured.setFeatured_position(1);
		featured.setBbcid(201l);
		featured.setIs_guide(0);
		featured.setIs_superuser(0);

		playlistUpdated = new PlaylistUpdated();
		playlistUpdated.setPromoted_at(dateUtils.timestamp());
		playlistUpdated.setTitle("playlist");
		List<PlaylistUpdated> listPlaylistUpdated = new ArrayList();
		listPlaylistUpdated.add(playlistUpdated);

		featured.setPlaylist_last_updated(listPlaylistUpdated);

		collectionUpdated = new CollectionUpdated();
		collectionUpdated.setPromoted_at(dateUtils.timestamp());
		collectionUpdated.setTitle("collection");
		List<CollectionUpdated> listCollectionUpdated = new ArrayList();
		listCollectionUpdated.add(collectionUpdated);

		featured.setCollection_last_updated(listCollectionUpdated);

		guides = new Users();
		guides.setCollection_last_updated(listCollectionUpdated);
		guides.setPlaylist_last_updated(listPlaylistUpdated);
		guides.setName("name");
		guides.setUsername(GUIDE);
		guides.setFeatured_position(0);
		guides.setBbcid(202l);
		guides.setIs_guide(0);
		guides.setIs_superuser(0);

		urlKeysDao = (UrlKeysDao) ctx.getBean("urlKeysDao");
		usersDao = (UsersDao) ctx.getBean("usersDao");
		brandsDao = (BrandsDao) ctx.getBean("brandsDao");
		playlistsDao = (PlaylistsDao) ctx.getBean("playlistsDao");
		tracksDao = (TracksDao) ctx.getBean("tracksDao");
		collectionsDao = (CollectionsDao) ctx.getBean("collectionsDao");
		clipsDao = (ClipsDao) ctx.getBean("clipsDao");

	}


}
