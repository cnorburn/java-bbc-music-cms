package bbc.forge.music.dao;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bbc.forge.music.model.BrandUser;
import bbc.forge.music.model.Brands;
import bbc.forge.music.model.Clips;
import bbc.forge.music.model.CollectionUpdated;
import bbc.forge.music.model.Collections;
import bbc.forge.music.model.Guides;
import bbc.forge.music.model.Playlists;
import bbc.forge.music.model.Tracks;
import bbc.forge.music.model.TracksPlaylist;
import bbc.forge.music.model.Users;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class DaoTest extends BaseDao {

	@BeforeClass
	public static void setup() {
		ctx = new ClassPathXmlApplicationContext("testContext.xml");
	}
	protected String[] getConfigLocations() {
		return new String[] { "testContext.xml" };
	}

	@Override
	@Before
	public void main() {
		super.main();
	}
	
	private void deleteTestUsers(){
		log.logTest("deleting any residual test users left in the db....");
		
		List <Guides> guides=usersDao.fetchGuide(FEATURED);
		for (Guides guide : guides) {
			usersDao.delete(guide);
		}

		guides=usersDao.fetchGuide(FEATURED_UPDATED);
		for (Guides guide : guides) {
			usersDao.delete(guide);
		}
		guides=usersDao.fetchGuide(GUIDE);
		for (Guides guide : guides) {
			usersDao.delete(guide);
		}

		
	}
	
	
	@Test
	public void deleteUsers(){
		deleteTestUsers();
	}
	
	@Test
	public void insertFeatured() {
		log.logTest("guides insert featured");

		usersDao.insert(featured);

		List<CollectionUpdated> collections = featured.getCollection_last_updated();
		log.logTest("collections - " + collections.size());

		log.logTest("inserted featured id - " + featured.getId());
		assertNotNull("inserted new featured guide", featured.getId());
	}
	
	@Test
	public void updateFeatured() {
		featured = usersDao.fetch(FEATURED);
		
		log.logTest("update the featured user - id - " + featured.getId());

		featured.setUsername(FEATURED_UPDATED);
		usersDao.insert(featured);

		Users u = usersDao.fetch(FEATURED_UPDATED);
		assertNotNull("updated featured user", u.getId());

	}


	@Test
	public void insertGuide() {
		log.logTest("guides insert guide");
		Users g = usersDao.insert(guides);
		assertNotNull("inserted new guide", g.getId());
	}

	
	@Test
	public void insertBrands() {
		log.logTest("insert some user brands");

		brand.setTitle("BBC Brand");
		brand.setBrand_url("url");
		brand.setMaster_brand("master brand");

		brandsDao.insert(brand);

		Users user = usersDao.fetch(FEATURED_UPDATED);

		log.logTest("user - " + user.getId());

		BrandUser userBrand = new BrandUser(user, brand);
		brandsDao.insert(userBrand);

		assertNotNull("inserted new brand", brand.getId());
		assertNotNull("inserted new brand to user relationship",
				userBrand.getId());

	}
	@Test
	/*Just getting the first returned brand, will have to 
	 * iterate them in the webapp to find which one to update
	 */
	public void updateBrand(){
		Users user = usersDao.fetch(FEATURED_UPDATED);

		log.logTest("updating a brand -  user id - " + user.getId());
		
		List<BrandUser> brandsUsers= brandsDao.fetchUserBrand(user.getId());
		
		log.logTest("returned " + brandsUsers.size() + " brand users");

		Brands brand=new Brands();
		BrandUser _userBrand=brandsUsers.get(0);
		
		_userBrand.setDisplay_order(99);
		brand=_userBrand.getBrand();
		
		brand.setTitle("1 Xtra");
		//save it
		brandsDao.insert(brand);
		brandsDao.insert(_userBrand);
		
		//retrieve it to to test value
		user = usersDao.fetch(FEATURED_UPDATED);
		brandsUsers= brandsDao.fetchUserBrand(user.getId());
		_userBrand=brandsUsers.get(0);
		brand=_userBrand.getBrand();

		assertEquals("updated brand",brand.getTitle(),"1 Xtra");
		assertEquals("updated brand display",_userBrand.getDisplay_order(),99);

	}
	
	@Test
	public void deleteBrand() {
		Users user = usersDao.fetch(FEATURED_UPDATED);

		log.logTest("deleting all user brands -  user id - " + user.getId());
		
		List<BrandUser> brandsUsers= brandsDao.fetchUserBrand(user.getId());
		
		for (BrandUser userBrand : brandsUsers) {
			Brands b=userBrand.getBrand();
			brandsDao.delete(userBrand);
			brandsDao.delete(b);
		}
		
	}

	
	@Test
	public void insertPlaylist() throws Exception {
		Users user = usersDao.fetch(FEATURED_UPDATED);
	
		log.logTest("insert a playlist for user id - " + user.getId());

		Playlists playlist = new Playlists();

		playlist.setMedium_synopsis("medium_synopsis");
		playlist.setShort_synopsis("short_synopsis");
		playlist.setTitle("title");
		playlist.setUrl_key(keygen.getUrlKey(urlKeysDao.generate()));
		playlist.setStatus(0);
		playlist.setUser_id(user.getId());

		playlistsDao.insert(playlist);

		playlistKey=playlist.getUrl_key();
		
		assertNotNull("inserted new playlist", playlist.getId());

	}
	
	@Test
	public void fetchAllPlaylists(){
		log.logTest("fetch ALL playlists");
	
		List<Playlists> playlists=playlistsDao.fetchAll();
		
		log.logTest("playlists fetched - " + playlists.size());
		
		assertTrue("fetched at least one playlist", playlists.size()>0);
			
	}
	
	@Test
	public void updatePlaylist(){
		log.logTest("update playlist - " + playlistKey);
	
		Playlists playlist=playlistsDao.fetch(playlistKey);
		
		playlist.setTitle("updated title");
		playlistsDao.insert(playlist);
		
		Playlists p=playlistsDao.fetch(playlistKey);

		log.logTest("update playlist - " +  p.getTitle());

		assertEquals("updated playlist",p.getTitle(),"updated title");
		
	}

	
	@Test
	public void fetchAllPlaylistsByStatus(){
		log.logTest("fetch playlists by status");
	
		List<Playlists> playlists=playlistsDao.fetchAll(0);
		
		log.logTest("playlists fetched - " + playlists.size());
		
		assertTrue("fetched at least one playlist by status", playlists.size()>0);
			
	}

	@Test
	public void insertTracks() throws Exception{
	
		log.logTest("inserting tracks ");

		Tracks t1=new Tracks();
		Tracks t2=new Tracks();

		t1.setArtist_gid("track 1 artist_gid");
		t1.setArtist_name("track 1 artist_name");
		t1.setTitle("track 1 title");
		t1.setTrack_gid("track 1 track_gid");
		t1.setUrl_key(keygen.getUrlKey(urlKeysDao.generate()));
		track1Key=t1.getUrl_key();
		
		t2.setArtist_gid("track 2 artist_gid");
		t2.setArtist_name("track 2 artist_name");
		t2.setTitle("track 2 title");
		t2.setTrack_gid("track 2 track_gid");
		t2.setUrl_key(keygen.getUrlKey(urlKeysDao.generate()));
		track2Key=t2.getUrl_key();
	
		tracksDao.insert(t1);
		tracksDao.insert(t2);
		
		Playlists playlist=playlistsDao.fetch(playlistKey);
		
		TracksPlaylist playListTrack1=new TracksPlaylist(playlist,t1);
		TracksPlaylist playListTrack2=new TracksPlaylist(playlist,t2);
		
		playListTrack1.setMedium_synopsis(" track 1 medium_synopsis");
		playListTrack1.setTrack_position(1);
	
		playListTrack2.setMedium_synopsis(" track 2 medium_synopsis");
		playListTrack2.setTrack_position(2);

		tracksDao.insert(playListTrack1);
		tracksDao.insert(playListTrack2);
			
		List<TracksPlaylist> tracksPlaylist=playlistsDao.fetch(playlist.getId());
		int i = 0;
		for (TracksPlaylist playlistTrack : tracksPlaylist) {
			Tracks _t=playlistTrack.getTrack();
			i++;
		}	
		assertEquals("inserted 2  tracks",i,2);
		assertEquals("inserted 2 playlist tracks",tracksPlaylist.size(),2);
		
	}
	
	
	@Test
	public void updateTrack(){
		log.logTest("update a track " +  track1Key);
		
		Tracks track=tracksDao.fetch(track1Key);
		
		track.setArtist_name("bowie");
		
		tracksDao.insert(track);
		
		track=tracksDao.fetch(track1Key);
		
		assertEquals("updated track",track.getArtist_name(),"bowie");

	}

	@Test 
	public void updatePlaylistTrack(){
		log.logTest("update a playlist track ");
		
		//Get the users playlist we want to update the playlist track on
		Playlists playlist=playlistsDao.fetch(playlistKey);
		
		List<TracksPlaylist> tracksPlaylist=playlistsDao.fetch(playlist.getId());
		
		//Get the track who's playlist track we want to update
		Tracks track=tracksDao.fetch(track2Key);
		
		for (TracksPlaylist playlistTrack : tracksPlaylist) {
			Tracks t=playlistTrack.getTrack();
			if(t.getId()==track.getId()){
				playlistTrack.setMedium_synopsis("updated medium synopsis");
				tracksDao.insert(playlistTrack);
			}
		}
		
		assertEquals("fetched 2 playlist tracks back", tracksPlaylist.size(), 2);
		
		//test if updated
		List<TracksPlaylist> _tracksPlaylist=playlistsDao.fetch(playlist.getId());
		for (TracksPlaylist playlistTrack : _tracksPlaylist) {
			Tracks t=playlistTrack.getTrack();
			if(t.getId()==track.getId()){
				assertEquals("playlist track updated", playlistTrack.getMedium_synopsis() , "updated medium synopsis");
			}
		}
		
	}
	
	@Test
	public void deletePlaylistTracks(){
		log.logTest("delete playlist tracks and tracks ");
	
		Playlists playlist=playlistsDao.fetch(playlistKey);
		List<TracksPlaylist> tracksPlaylist=playlistsDao.fetch(playlist.getId());
		for (TracksPlaylist trackPlaylist : tracksPlaylist) {
			Tracks t=trackPlaylist.getTrack();
			playlistsDao.delete(trackPlaylist);
			tracksDao.delete(t);
		}
		
		//Test
		playlist=playlistsDao.fetch(playlistKey);
		tracksPlaylist=playlistsDao.fetch(playlist.getId());
		assertTrue("deleted playlist tracks", tracksPlaylist.size()==0);

		
	}

	
	
	/*
	@Test
	public void instertExternalPlaylists(){
		log.logTest("inserting 4 external playlists for playlist - " + playlistKey);
	
		Playlist playlist=playlistMatDao.fetch(playlistKey);

		ExternalPlaylist extSpotify = new ExternalPlaylist();
		ExternalPlaylist extWe7 = new ExternalPlaylist();
		ExternalPlaylist extMySpace = new ExternalPlaylist();
		ExternalPlaylist extYouTube = new ExternalPlaylist();
		
		extSpotify.setPlaylist(playlist);
		extSpotify.setExternal_id(1l);
		extSpotify.setUrl("spotify url");
		extSpotify.setTracks(7);
		
		extWe7.setPlaylist(playlist);
		extWe7.setExternal_id(2l);
		extWe7.setUrl("we 7 url");
		extWe7.setTracks(9);
	
		extMySpace.setPlaylist(playlist);
		extMySpace.setExternal_id(3l);
		extMySpace.setUrl("my space url");
		extMySpace.setTracks(8);

		extYouTube.setPlaylist(playlist);
		extYouTube.setExternal_id(4l);
		extYouTube.setUrl("you tube url");
		extYouTube.setTracks(6);
		
		externalPlaylistMatDao.insert(extSpotify);
		externalPlaylistMatDao.insert(extWe7);
		externalPlaylistMatDao.insert(extMySpace);
		externalPlaylistMatDao.insert(extYouTube);
		
		List<ExternalPlaylist> externals=externalPlaylistMatDao.fetch(playlist);
		
		assertEquals("inserted external playlists",externals.size(),4);

	}
		@Test
	public void updateExternalPlaylists(){
		log.logTest("updating spotify external playlist for playlist - " + playlistKey);

		Playlist playlist=playlistMatDao.fetch(playlistKey);
		List<ExternalPlaylist> externals=externalPlaylistMatDao.fetch(playlist);
		for (ExternalPlaylist externalPlaylist : externals) {
			if(externalPlaylist.getExternal_id()==1l ){
				externalPlaylist.setTracks(99);
				externalPlaylistMatDao.insert(externalPlaylist);
			}
		}
		
		//test if updated
		List<ExternalPlaylist> _externals=externalPlaylistMatDao.fetch(playlist);
		for (ExternalPlaylist externalPlaylist : _externals) {
			if(externalPlaylist.getExternal_id()==1l )
				assertEquals("updated spotify playlist", externalPlaylist.getTracks(), 99);
		}

	}

	*/

	
	@Test
	public void insertCollection() throws Exception{
		log.logTest("insert a collection into the featured user ");

		Users user = usersDao.fetch(FEATURED_UPDATED);

		Collections collection=new Collections();
		
		collection.setUser_id(user.getId());
		collection.setMedium_synopsis("medium_synopsis");
		collection.setShort_synopsis("short_synopsis");
		collection.setTitle("title");
		collection.setPid("pid");
		collection.setUrl_key(keygen.getUrlKey(urlKeysDao.generate()));
		
		collectionsDao.insert(collection);
		
		collectionKey=collection.getUrl_key();
		
		assertNotNull("returned an inserted collection", collection.getId());
		
	}
	
	@Test
	public void updateCollection(){
		log.logTest("update the featured user's collection ");

		Collections collection=collectionsDao.fetch(collectionKey);
		
		collection.setMedium_synopsis("updated medium_synopsis");
		collectionsDao.insert(collection);
		
		//test
		collection=collectionsDao.fetch(collectionKey);
		assertEquals("updated collection", collection.getMedium_synopsis(), "updated medium_synopsis");

	}
	
	@Test
	public void insertClips(){
		log.logTest("inserting clips into collection  - " + collectionKey);

		Collections collection=collectionsDao.fetch(collectionKey);
		
		Clips c1=new Clips();
		Clips c2=new Clips();
		
		c1.setPid("pid1");
		c1.setTitle("title 1");
		c1.setDisplay_order(1);
		c1.setCollection_id(collection.getId());
		
		c2.setPid("pid2");
		c2.setTitle("title 2");
		c2.setDisplay_order(2);
		c2.setCollection_id(collection.getId());
		
		clipsDao.insert(c1);
		clipsDao.insert(c2);
		
		//test
		List<Clips> clips=clipsDao.fetchAll(collection);
		
		assertEquals("inserted 2 clips", clips.size(), 2);
		
	}
	
	@Test
	public void updateClip(){
		log.logTest("update a clip in collection  - " + collectionKey);
	
		Collections collection=collectionsDao.fetch(collectionKey);
		List<Clips> clips=clipsDao.fetchAll(collection);
		for (Clips clip : clips) {
			if (clip.getPid().equals("pid1")){
				clip.setTitle("updated title");
				clipsDao.insert(clip);
			}
		}
		
		//test
		List<Clips> _clips=clipsDao.fetchAll(collection);
		for (Clips clip : _clips) {
			if (clip.getPid().equals("pid1")){
				assertEquals("updated clip 1 title", clip.getTitle(), "updated title");
			}
		}

		
	}
	
	@Test
	public void deleteClips(){
		log.logTest("deleting clips in collection  - " + collectionKey);
		
		Collections collection=collectionsDao.fetch(collectionKey);
		List<Clips> clips=clipsDao.fetchAll(collection);
		for (Clips clip : clips) 
			clipsDao.delete(clip);
		
		 clips=clipsDao.fetchAll(collection);
		 assertEquals("deleted clips",clips.size(),0);
		
	}
	
	
	@Test
	public void deleteCollection(){
		log.logTest("delete the featured user's collection ");
		Collections collection=collectionsDao.fetch(collectionKey);

		collectionsDao.delete(collection);
		
		//test
		collection=collectionsDao.fetch(collectionKey);
		assertTrue("deleted featured user's collection", collection == null);

	}
	
	/*
	@Test
	public void deleteExternalPlaylists(){
		log.logTest("deleting all  external playlists for playlist - " + playlistKey);
	
		Playlist playlist=playlistMatDao.fetch(playlistKey);
		List<ExternalPlaylist> externals=externalPlaylistMatDao.fetch(playlist);
		for (ExternalPlaylist externalPlaylist : externals) {
			externalPlaylistMatDao.delete(externalPlaylist);
		}
		
		//test if deleted
		List<ExternalPlaylist> _externals=externalPlaylistMatDao.fetch(playlist);
	
		assertEquals("deleted all external playlists for playlist",_externals.size(),0);
		
	}
	*/
	@Test
	public void deletePlaylist(){
		log.logTest("deleting playlist - " + playlistKey);

		Playlists playlist=playlistsDao.fetch(playlistKey);
		
		playlistsDao.delete(playlist);
		
		playlist=playlistsDao.fetch(playlistKey);
		assertTrue("deleted playlist",playlist== null);

	}

	@Test
	public void cleanDb(){
		deleteTestUsers();
	}
}
