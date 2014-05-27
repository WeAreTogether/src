package algorithm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBConnection;



public class Database {
	
	
	private static Connection conn=null;

	private Statement st;
	
	
	public Database() {
		try{
			conn = new DBConnection().getConnection();
			if(conn != null){
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int getLecturersSize() throws SQLException{
		ResultSet qrs;
		
		String query = new String("SELECT count(*) FROM algo.lecturer");
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qrs = st.executeQuery(query);
		qrs.first();
		return qrs.getInt(1);
		
	}
	
	public int getClassSize() throws SQLException{
		ResultSet qrs;
		
		String query = new String("SELECT count(*) FROM algo.classes");
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qrs = st.executeQuery(query);
		qrs.first();
		return qrs.getInt(1);
		
	}
	
	public int getCoursesSize() throws SQLException{
		ResultSet qrs;
		
		String query = new String("SELECT count(*) FROM algo.course");
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qrs = st.executeQuery(query);
		qrs.first();
		return qrs.getInt(1);
		
	}
	
	public int getAcademicHours(int CourseId) throws SQLException{
		ResultSet qrs;
		
		String query = new String("SELECT academicHours FROM algo.course where courseid=?");
		 PreparedStatement ab = conn.prepareStatement(query);
		 ab.setInt(1, CourseId);
		 qrs=ab.executeQuery();
			qrs.first();
		return qrs.getInt(1);
		
	}
}

//the new code
Room.h

#include <string>

#pragma once

using namespace std;

// Stores data about classroom

class Room

{

private:

    // ID counter used to assign IDs automatically

    static int _nextRoomId;

private:

    // Room ID - automatically assigned

    int _id;

    // Room name

    string _name;

    // Inidicates if room has computers

    bool _lab;

    // Number of seats in room

    int _numberOfSeats;

public:

    // Initializes room data and assign ID to room

    Room(const string& name, bool lab, int numberOfSeats);

    // Returns room ID

    inline int GetId() const { return _id; }

    // Returns name

    inline const string& GetName() const { return _name; }

    // Returns TRUE if room has computers otherwise it returns FALSE

    inline bool IsLab() const { return _lab; }

    // Returns number of seats in room

    inline int GetNumberOfSeats() const { return _numberOfSeats; }

    // Restarts ID assigments

    static inline void RestartIDs() { _nextRoomId = 0; }

};

==================================================================================

Room.cpp

#include "stdafx.h"

#include "Room.h"

int Room::_nextRoomId = 0;

// Initializes room data and assign ID to room

Room::Room(const string& name, bool lab, int numberOfSeats) : _id(_nextRoomId++),

                                                                     _name(name),

                                                                     _lab(lab),

                                                                     _numberOfSeats(numberOfSeats) { }


Professor.h


#include <list>

#include <string>

#pragma once

using namespace std;

class CourseClass;

// Stores data about professor

class Professor

{

private:

    // Professor's ID

    int _id;

    //

    string _name;

    // List of classes that professor teaches

    list<CourseClass*> _courseClasses;

public:

    // Initializes professor data

    Professor(int id, const string& name);

    // Bind professor to course

    void AddCourseClass(CourseClass* courseClass);

    // Returns professor's ID

    inline int GetId() const { return _id; }

    // Returns professor's name

    inline const string& GetName() const { return _name; }

    // Returns reference to list of classes that professor teaches

    inline const list<CourseClass*>& GetCourseClasses() const { return _courseClasses; }

    // Compares ID's of two objects which represent professors

    inline bool operator ==(const Professor& rhs) const { return _id == rhs._id; }

};


Professor.cpp


#include "stdafx.h"

#include "Professor.h"

// Initializes professor data

Professor::Professor(int id, const string& name) : _id(id),

                                                  _name(name) { }

// Bind professor to course

void Professor::AddCourseClass(CourseClass* courseClass)

{

    _courseClasses.push_back( courseClass );

}



CourseClass.h


#include <list>

#include "Professor.h"

#include "StudentsGroup.h"

#include "Course.h"

#pragma once

using namespace std;

// Stores data about single class

class CourseClass

{

private:

    // Profesor who teaches

    Professor* _professor;

    // Course to which class belongs

    Course* _course;

    // Student groups who attend class

    list<StudentsGroup*> _groups;

    // Number of seats (students) required in room

    int _numberOfSeats;

    // Initicates wheather class requires computers

    bool _requiresLab;

    // Duration of class in hours

    int _duration;

   

public:

    // Initializes class object

    CourseClass(Professor* professor, Course* course, const list<StudentsGroup*>& groups,

        bool requiresLab, int duration);

    // Frees used memory

    ~CourseClass();

    // Returns TRUE if another class has one or overlapping student groups.

    bool GroupsOverlap(const CourseClass& c ) const;

    // Returns TRUE if another class has same professor.

    inline bool ProfessorOverlaps(const CourseClass& c ) const { return *_professor == *c._professor; }

    // Return pointer to professor who teaches

    inline const Professor& GetProfessor() const { return *_professor; }

    // Return pointer to course to which class belongs

    inline const Course& GetCourse() const { return *_course; }

    // Returns reference to list of student groups who attend class

    inline const list<StudentsGroup*>& GetGroups() const { return _groups; }

    // Returns number of seats (students) required in room

    inline int GetNumberOfSeats() const { return _numberOfSeats; }

    // Returns TRUE if class requires computers in room.

    inline bool IsLabRequired() const { return _requiresLab; }

    // Returns duration of class in hours

    inline int GetDuration() const { return _duration; }

};


CourseClass.cpp


#include "stdafx.h"

#include "CourseClass.h"

// Initializes class object

CourseClass::CourseClass(Professor* professor, Course* course, const list<StudentsGroup*>& groups,

                        bool requiresLab, int duration) :

                        _professor(professor),

                        _course(course),

                        _numberOfSeats(0),

                        _requiresLab(requiresLab),

                        _duration(duration)

{

    // bind professor to class

    _professor->AddCourseClass( this );

    // bind student groups to class

    for( list<StudentsGroup*>::const_iterator it = groups.begin(); it != groups.end(); it++ )

    {

        ( *it )->AddClass( this );

        _groups.push_back( *it );

        _numberOfSeats += ( *it )->GetNumberOfStudents();

    }

}

// Frees used memory

CourseClass::~CourseClass() { }

// Returns TRUE if another class has one or overlapping student groups.

bool CourseClass::GroupsOverlap(const CourseClass& c ) const

{

    for( list<StudentsGroup*>::const_iterator it1 = _groups.begin(); it1 != _groups.end(); it1++ )

    {

        for( list<StudentsGroup*>::const_iterator it2 = c._groups.begin(); it2 != c._groups.end(); it2++ )

        {

            if( *it1 == *it2 )

                return true;

        }

    }

    return false;

}

Course.h



#include <string>

#pragma once

using namespace std;

// Stores data about course

class Course

{

private:

    // Course ID

    int _id;

    // Course name

    string _name;

public:

    // Initializes course

    Course(int id, const string& name);

    // Returns course ID

    inline int GetId() const { return _id; }

    // Returns course name

    inline const string& GetName() const { return _name; }

};


Course.cpp

#include "stdafx.h"

#include "Course.h"

// Initializes course

Course::Course(int id, const string& name) : _id(id),

                                            _name(name) { }


StudentsGroup.h


#include <list>

#include <string>

#pragma once

using namespace std;

class CourseClass;

// Stores data about student group

class StudentsGroup

{

private:

    // Student group ID

    int _id;

    // Name of student group

    string _name;

    // Number of students in group

    int _numberOfStudents;

    // List of classes that group attends

    list<CourseClass*> _courseClasses;

public:

    // Initializes student group data

    StudentsGroup(int id, const string& name, int numberOfStudents);

    // Bind group to class

    void AddClass(CourseClass* courseClass);

    // Returns student group ID

    inline int GetId() const { return _id; }

    // Returns name of student group

    inline const string& GetName() const { return _name; }

    // Returns number of students in group

    inline int GetNumberOfStudents() const { return _numberOfStudents; }

    // Returns reference to list of classes that group attends

    inline const list<CourseClass*>& GetCourseClasses() const { return _courseClasses; }

    // Compares ID's of two objects which represent student groups

    inline bool operator ==(const StudentsGroup& rhs) const { return _id == rhs._id; }

};



StudentsGroup.cpp


#include "stdafx.h"

#include "StudentsGroup.h"

// Initializes student group data

StudentsGroup::StudentsGroup(int id, const string& name, int numberOfStudents) : _id(id),

                                                                                _name(name),

                                                                                _numberOfStudents(numberOfStudents)

{

}

// Bind group to class

void StudentsGroup::AddClass(CourseClass* courseClass)

{

    _courseClasses.push_back( courseClass );

}



Configuration.h



#include <list>

#include <hash_map>

#include <fstream>

#include <string>

#pragma once

using namespace std;

using namespace stdext;

class Professor;

class StudentsGroup;

class Course;

class Room;

class CourseClass;

// Reads configration file and stores parsed objects

class Configuration

{

private:

    // Global instance

    static Configuration _instance;

public:

    // Returns reference to global instance

    inline static Configuration& GetInstance() { return _instance; }

private:

    // Parsed professors

    hash_map<int, Professor*> _professors;

    // Parsed student groups

    hash_map<int, StudentsGroup*> _studentGroups;

    // Parsed courses

    hash_map<int, Course*> _courses;

    // Parsed rooms

    hash_map<int, Room*> _rooms;

    // Parsed classes

    list<CourseClass*> _courseClasses;

    // Inidicate that configuration is not prsed yet

    bool _isEmpty;

public:

    // Initialize data

    Configuration() : _isEmpty(true) { }

    // Frees used resources

    ~Configuration();

    // Parse file and store parsed object

    void ParseFile(char* fileName);

    // Returns pointer to professor with specified ID

    // If there is no professor with such ID method returns NULL

    inline Professor* GetProfessorById(int id)

    {

        hash_map<int, Professor*>::iterator it = _professors.find( id );

        return it != _professors.end() ? ( *it ).second : NULL;

    }

    // Returns number of parsed professors

    inline int GetNumberOfProfessors() const { return (int)_professors.size(); }

    // Returns pointer to student group with specified ID

    // If there is no student group with such ID method returns NULL

    inline StudentsGroup* GetStudentsGroupById(int id)

    {

        hash_map<int, StudentsGroup*>::iterator it = _studentGroups.find( id );

        return it != _studentGroups.end() ? ( *it ).second : NULL;

    }

    // Returns number of parsed student groups

    inline int GetNumberOfStudentGroups() const { return (int)_studentGroups.size(); }

    // Returns pointer to course with specified ID

    // If there is no course with such ID method returns NULL

    inline Course* GetCourseById(int id)

    {

        hash_map<int, Course*>::iterator it = _courses.find( id );

        return it != _courses.end() ? ( *it ).second : NULL;

    }

    inline int GetNumberOfCourses() const { return (int)_courses.size(); }

    // Returns pointer to room with specified ID

    // If there is no room with such ID method returns NULL

    inline Room* GetRoomById(int id)

    {

        hash_map<int, Room*>::iterator it = _rooms.find( id );

        return it != _rooms.end() ? ( *it ).second : NULL;

    }

    // Returns number of parsed rooms

    inline int GetNumberOfRooms() const { return (int)_rooms.size(); }

    // Returns reference to list of parsed classes

    inline const list<CourseClass*>& GetCourseClasses() const { return _courseClasses; }

    // Returns number of parsed classes

    inline int GetNumberOfCourseClasses() const { return (int)_courseClasses.size(); }

    // Returns TRUE if configuration is not parsed yet

    inline bool IsEmpty() const { return _isEmpty; }

private:

    // Reads professor's data from config file, makes object and returns pointer to it

    // Returns NULL if method cannot parse configuration data

    Professor* ParseProfessor(ifstream& file);

    // Reads professor's data from config file, makes object and returns pointer to it

    // Returns NULL if method cannot parse configuration data

    StudentsGroup* ParseStudentsGroup(ifstream& file);

    // Reads course's data from config file, makes object and returns pointer to it

    // Returns NULL if method cannot parse configuration data

    Course* ParseCourse(ifstream& file);

    // Reads rooms's data from config file, makes object and returns pointer to it

    // Returns NULL if method cannot parse configuration data

    Room* ParseRoom(ifstream& file);

    // Reads class' data from config file, makes object and returns pointer to it

    // Returns NULL if method cannot parse configuration data

    CourseClass* ParseCourseClass(ifstream& file);

    // Reads one line (key - value pair) from configuration file

    bool GetConfigBlockLine(ifstream& file, string& key, string& value);

    // Removes blank characters from beginning and end of string

    string& TrimString(string& str);

};


Configuration.cpp

#include "stdafx.h"

#include "Configuration.h"

#include "Professor.h"

#include "StudentsGroup.h"

#include "Course.h"

#include "Room.h"

#include "CourseClass.h"

Configuration Configuration::_instance;

// Frees used resources

Configuration::~Configuration()

{

    for( hash_map<int, Professor*>::iterator it = _professors.begin(); it != _professors.end(); it++ )

        delete ( *it ).second;

    for( hash_map<int, StudentsGroup*>::iterator it = _studentGroups.begin(); it != _studentGroups.end(); it++ )

        delete ( *it ).second;

    for( hash_map<int, Course*>::iterator it = _courses.begin(); it != _courses.end(); it++ )

        delete ( *it ).second;

    for( hash_map<int, Room*>::iterator it = _rooms.begin(); it != _rooms.end(); it++ )

        delete ( *it ).second;

    for( list<CourseClass*>::iterator it = _courseClasses.begin(); it != _courseClasses.end(); it++ )

        delete *it;

}

// Parse file and store parsed object

void Configuration::ParseFile(char* fileName)

{

    // clear previously parsed objects

    _professors.clear();

    _studentGroups.clear();

    _courses.clear();

    _rooms.clear();

    _courseClasses.clear();

    Room::RestartIDs();

    // open file

    ifstream input( fileName );

    string line;

    while( input.is_open() && !input.eof() )

    {

        // get lines until start of new object is not found

        getline( input, line );

        TrimString( line );

        // get type of object, parse obect and store it

        if( line.compare("#prof") == 0 )

        {

            Professor* p = ParseProfessor( input );

            if( p )

                _professors.insert( pair<int, Professor*>( p->GetId(), p ) );

        }

        else if( line.compare("#group") == 0 )

        {

            StudentsGroup* g = ParseStudentsGroup( input );

            if( g )

                _studentGroups.insert( pair<int, StudentsGroup*>( g->GetId(), g ) );

        }

        else if( line.compare("#course") == 0 )

        {

            Course* c = ParseCourse( input );

            if( c )

                _courses.insert( pair<int, Course*>( c->GetId(), c ) );

        }

        else if( line.compare("#room") == 0 )

        {

            Room* r = ParseRoom( input );

            if( r )

                _rooms.insert( pair<int, Room*>( r->GetId(), r ) );

        }

        else if( line.compare("#class") == 0 )

        {

            CourseClass* c = ParseCourseClass( input );

            if( c )

                _courseClasses.push_back( c );

        }

    }

    input.close();

    _isEmpty = false;

}

// Reads professor's data from config file, makes object and returns pointer to it

// Returns NULL if method cannot parse configuration data

Professor* Configuration::ParseProfessor(ifstream& file)

{

    int id = 0;

    string name;

    while( !file.eof() )

    {

        string key, value;

        // get key - value pair

        if( !GetConfigBlockLine( file, key, value ) )

            break;

        // get value of key

        if( key.compare("id") == 0 )

            id = atoi( value.c_str() );

        else if( key.compare("name") == 0 )

            name = value;

    }

    // make object and return pointer to it

    return id == 0 ? NULL : new Professor( id, name );

}

// Reads professor's data from config file, makes object and returns pointer to it

// Returns NULL if method cannot parse configuration data

StudentsGroup* Configuration::ParseStudentsGroup(ifstream& file)

{

    int id = 0, number = 0;

    string name;

    while( !file.eof() )

    {

        string key, value;

        // get key - value pair

        if( !GetConfigBlockLine( file, key, value ) )

            break;

        // get value of key

        if( key.compare("id") == 0 )

            id = atoi( value.c_str() );

        else if( key.compare("name") == 0 )

            name = value;

        else if( key.compare("size") == 0 )

            number = atoi( value.c_str() );

    }

    // make object and return pointer to it

    return id == 0 ? NULL : new StudentsGroup( id, name, number );

}

// Reads course's data from config file, makes object and returns pointer to it

// Returns NULL if method cannot parse configuration data

Course* Configuration::ParseCourse(ifstream& file)

{

    int id = 0;

    string name;

    while( !file.eof() )

    {

        string key, value;

        // get key - value pair

        if( !GetConfigBlockLine( file, key, value ) )

            break;

        // get value of key

        if( key.compare("id") == 0 )

            id = atoi( value.c_str() );

        else if( key.compare("name") == 0 )

            name = value;

    }

    // make object and return pointer to it

    return id == 0 ? NULL : new Course( id, name );

}

// Reads rooms's data from config file, makes object and returns pointer to it

// Returns NULL if method cannot parse configuration data

Room* Configuration::ParseRoom(ifstream& file)

{

    int number = 0;

    bool lab = false;

    string name;

    while( !file.eof() )

    {

        string key, value;

        // get key - value pair

        if( !GetConfigBlockLine( file, key, value ) )

            break;

        // get value of key

        if( key.compare("name") == 0 )

            name = value;

        else if( key.compare("lab") == 0 )

            lab = value.compare( "true" ) == 0;

        else if( key.compare("size") == 0 )

            number = atoi( value.c_str() );

    }

    // make object and return pointer to it

    return number == 0 ? NULL : new Room( name, lab, number );

}

// Reads class' data from config file, makes object and returns pointer to it

// Returns NULL if method cannot parse configuration data

CourseClass* Configuration::ParseCourseClass(ifstream& file)

{

    int pid = 0, cid = 0, dur = 1;

    bool lab = false;

    list<StudentsGroup*> groups;

    while( !file.eof() )

    {

        string key, value;

        // get key - value pair

        if( !GetConfigBlockLine( file, key, value ) )

            break;

        // get value of key

        if( key.compare("professor") == 0 )

            pid = atoi( value.c_str() );

        else if( key.compare("course") == 0 )

            cid = atoi( value.c_str() );

        else if( key.compare("lab") == 0 )

            lab = value.compare( "true" ) == 0;

        else if( key.compare("duration") == 0 )

            dur = atoi( value.c_str() );

        else if( key.compare("group") == 0 )

        {

            StudentsGroup* g = GetStudentsGroupById( atoi( value.c_str() ) );

            if( g )

                groups.push_back( g );

        }

    }

    // get professor who teaches class and course to which this class belongs

    Professor* p = GetProfessorById( pid );

    Course* c = GetCourseById( cid );

    // does professor and class exists

    if( !c || !p )

        return NULL;

    // make object and return pointer to it

    CourseClass* cc = new CourseClass( p, c, groups, lab, dur );

    return cc;

}

// Reads one line (key - value pair) from configuration file

bool Configuration::GetConfigBlockLine(ifstream& file, string& key, string& value)

{

    string line;

    // end of file

    while( !file.eof() )

    {

        // read line from config file

        getline( file, line );

        TrimString( line );

        // end of object's data

        if( line.compare( "#end" ) == 0 )

            return false;

        size_t p = line.find( '=' );

        if( p != string.npos )

        {

            // key

            key = line.substr( 0, p );

            TrimString( key );

            // value

            value = line.substr( p + 1, line.length() );

            TrimString( value );

            // key - value pair read successfully

            return true;

        }

    }

    // error

    return false;

}

// Removes blank characters from beginning and end of string

string& Configuration::TrimString(string& str)

{

    string::iterator it;

    for( it = str.begin(); it != str.end() && isspace( *it ); it++ )

        ;

    str.erase( str.begin(), it );

    string::reverse_iterator rit;

    for( rit = str.rbegin(); rit != str.rend() && isspace( *rit ) ; rit++ )

        ;

    str.erase( str.begin() + ( str.rend() - rit ), str.end() );

    return str;

}



Schedule.h


#include <list>

#include <hash_map>

#include <windows.h>

#include "CourseClass.h"

#pragma once

using namespace std;

using namespace stdext;

class CChildView;

class Schedule;

class Algorithm;

// Number of working hours per day

#define DAY_HOURS    8

// Number of days in week

#define DAYS_NUM    5

enum AlgorithmState

{

    AS_USER_STOPED,

    AS_CRITERIA_STOPPED,

    AS_RUNNING

};

// Algorithm's observer

class ScheduleObserver

{

private:

    // Event that blocks caller until algorithm finishes execution

    HANDLE _event;

    // Window which displays schedule

    CChildView* _window;

    // Called when algorithm starts execution

    inline void BlockEvent() { ResetEvent( _event ); }

    // Called when algorithm finishes execution

    inline void ReleaseEvent() { SetEvent( _event ); }

public:

    // Initializes observer

    ScheduleObserver() : _window(NULL) { _event = CreateEvent( NULL, TRUE, FALSE, NULL ); }

    // Frees used resources

    ~ScheduleObserver() { CloseHandle( _event ); }

    // Block caller's thread until algorithm finishes execution

    inline void WaitEvent() { WaitForSingleObject( _event, INFINITE ); }

   

    // Handles event that is raised when algorithm finds new best chromosome

    void NewBestChromosome(const Schedule& newChromosome);

    // Handles event that is raised when state of execution of algorithm is changed

    void EvolutionStateChanged(AlgorithmState newState);

    // Sets window which displays schedule

    inline void SetWindow(CChildView* window) { _window = window; }

};

// Schedule chromosome

class Schedule

{

    friend class ScheduleObserver;

private:

    // Number of crossover points of parent's class tables

    int _numberOfCrossoverPoints;

    // Number of classes that is moved randomly by single mutation operation

    int _mutationSize;

    // Probability that crossover will occure

    int _crossoverProbability;

    // Probability that mutation will occure

    int _mutationProbability;

    // Fitness value of chromosome

    float _fitness;

    // Flags of class requiroments satisfaction

    vector<bool> _criteria;

    // Time-space slots, one entry represent one hour in one classroom

    vector<list<CourseClass*>> _slots;

    // Class table for chromosome

    // Used to determine first time-space slot used by class

    hash_map<CourseClass*, int> _classes;

public:

    // Initializes chromosomes with configuration block (setup of chromosome)

    Schedule(int numberOfCrossoverPoints, int mutationSize,

        int crossoverProbability, int mutationProbability);

    // Copy constructor

    Schedule(const Schedule& c, bool setupOnly);

    // Makes copy ot chromosome

    Schedule* MakeCopy(bool setupOnly) const;

    // Makes new chromosome with same setup but with randomly chosen code

    Schedule* MakeNewFromPrototype() const;

    // Performes crossover operation using to chromosomes and returns pointer to offspring

    Schedule* Crossover(const Schedule& parent2) const;

    // Performs mutation on chromosome

    void Mutation();

    // Calculates fitness value of chromosome

    void CalculateFitness();

    // Returns fitness value of chromosome

    float GetFitness() const { return _fitness; }

    // Returns reference to table of classes

    inline const hash_map<CourseClass*, int>& GetClasses() const { return _classes; }

    // Returns array of flags of class requiroments satisfaction

    inline const vector<bool>& GetCriteria() const { return _criteria; }

    // Return reference to array of time-space slots

    inline const vector<list<CourseClass*>>& GetSlots() const { return _slots; }

};

// Genetic algorithm

class Algorithm

{

private:

    // Population of chromosomes

    vector<Schedule*> _chromosomes;

    // Inidicates wheahter chromosome belongs to best chromosome group

    vector<bool> _bestFlags;

    // Indices of best chromosomes

    vector<int> _bestChromosomes;

    // Number of best chromosomes currently saved in best chromosome group

    int _currentBestSize;

    // Number of chromosomes which are replaced in each generation by offspring

    int _replaceByGeneration;

    // Pointer to algorithm observer

    ScheduleObserver* _observer;

    // Prototype of chromosomes in population

    Schedule* _prototype;

    // Current generation

    int _currentGeneration;

    // State of execution of algorithm

    AlgorithmState _state;

    // Synchronization of algorithm's state

    CCriticalSection _stateSect;

    // Pointer to global instance of algorithm

    static Algorithm* _instance;

    // Synchronization of creation and destruction of global instance

    static CCriticalSection _instanceSect;

public:

    // Returns reference to global instance of algorithm

    static Algorithm& GetInstance();

    // Frees memory used by gloval instance

    static void FreeInstance();

    // Initializes genetic algorithm

    Algorithm(int numberOfChromosomes, int replaceByGeneration, int trackBest,

        Schedule* prototype, ScheduleObserver* observer);

    

    // Frees used resources

    ~Algorithm();

    // Starts and executes algorithm

    void Start();

    // Stops execution of algoruthm

    void Stop();

    // Returns pointer to best chromosomes in population

    Schedule* GetBestChromosome() const;

    // Returns current generation

    inline int GetCurrentGeneration() const { return _currentGeneration; }

    // Returns pointe to algorithm's observer

    inline ScheduleObserver* GetObserver() const { return _observer; }

private:

    // Tries to add chromosomes in best chromosome group

    void AddToBest(int chromosomeIndex);

    // Returns TRUE if chromosome belongs to best chromosome group

    bool IsInBest(int chromosomeIndex);

    // Clears best chromosome group

    void ClearBest();

    // Initializes Tabu Search algorithm

    void TabuSearch(int chromosomeIndex);

  // Returns TRUE if chromosome belongs to best chromosome group

    bool TabuIsInBest(int chromosomeIndex);

    // Clears best chromosome group

    //void TabuClearBest();

};



Schedule.cpp


#include "stdafx.h"

#include <iostream>

#include <vector>

#include "..\ChildView.h"

#include "Configuration.h"

#include "Schedule.h"

#include "Room.h"

// Handles event that is raised when algorithm finds new best chromosome

void ScheduleObserver::NewBestChromosome(const Schedule& newChromosome)

{

    if( _window )

        _window->SetSchedule( &newChromosome );

}

// Handles event that is raised when state of execution of algorithm is changed

void ScheduleObserver::EvolutionStateChanged(AlgorithmState newState)

{

    if( _window )

        _window->SetNewState( newState );

    newState != AS_RUNNING ? ReleaseEvent() : BlockEvent();

}

// Initializes chromosomes with configuration block (setup of chromosome)

Schedule::Schedule(int numberOfCrossoverPoints, int mutationSize,

                  int crossoverProbability, int mutationProbability) : _mutationSize(mutationSize),

                  _numberOfCrossoverPoints(numberOfCrossoverPoints),

                  _crossoverProbability(crossoverProbability),

                  _mutationProbability(mutationProbability),

                  _fitness(0)

{

    // reserve space for time-space slots in chromosomes code

    _slots.resize( DAYS_NUM * DAY_HOURS * Configuration::GetInstance().GetNumberOfRooms() );

    // reserve space for flags of class requirements

    _criteria.resize( Configuration::GetInstance().GetNumberOfCourseClasses() * 5 );

}

// Copy constructor

Schedule::Schedule(const Schedule& c, bool setupOnly)

{

    if( !setupOnly )

    {

        // copy code

        _slots = c._slots;

        _classes = c._classes;

        // copy flags of class requirements

        _criteria = c._criteria;

        // copy fitness

        _fitness = c._fitness;

    }

    else

    {

        // reserve space for time-space slots in chromosomes code

        _slots.resize( DAYS_NUM * DAY_HOURS * Configuration::GetInstance().GetNumberOfRooms() );

        // reserve space for flags of class requirements

        _criteria.resize( Configuration::GetInstance().GetNumberOfCourseClasses() * 5 );

    }

    // copy parameters

    _numberOfCrossoverPoints = c._numberOfCrossoverPoints;

    _mutationSize = c._mutationSize;

    _crossoverProbability = c._crossoverProbability;

    _mutationProbability = c._mutationProbability;

}

// Makes copy ot chromosome

Schedule* Schedule::MakeCopy(bool setupOnly) const

{

    // make object by calling copy constructor and return smart pointer to new object

    return new Schedule( *this, setupOnly );

}

// Makes new chromosome with same setup but with randomly chosen code

Schedule* Schedule::MakeNewFromPrototype() const

{

    // number of time-space slots

    int size = (int)_slots.size();

    // make new chromosome, copy chromosome setup

    Schedule* newChromosome = new Schedule( *this, true );

    // place classes at random position

    const list<CourseClass*>& c = Configuration::GetInstance().GetCourseClasses();

    for( list<CourseClass*>::const_iterator it = c.begin(); it != c.end(); it++ )

    {

        // determine random position of class

        int nr = Configuration::GetInstance().GetNumberOfRooms();

        int dur = ( *it )->GetDuration();

        int day = rand() % DAYS_NUM;

        int room = rand() % nr;

        int time = rand() % ( DAY_HOURS + 1 - dur );

        int pos = day * nr * DAY_HOURS + room * DAY_HOURS + time;

        // fill time-space slots, for each hour of class

        for( int i = dur - 1; i >= 0; i-- )

            newChromosome->_slots.at( pos + i ).push_back( *it );

        // insert in class table of chromosome

        newChromosome->_classes.insert( pair<CourseClass*, int>( *it, pos ) );

    }

    newChromosome->CalculateFitness();

    // return smart pointer

    return newChromosome;

}

// Performes crossover operation using to chromosomes and returns pointer to offspring

Schedule* Schedule::Crossover(const Schedule& parent2) const

{

    // check probability of crossover operation

    if( rand() % 100 > _crossoverProbability )

        // no crossover, just copy first parent

        return new Schedule( *this, false );

    // new chromosome object, copy chromosome setup

    Schedule* n = new Schedule( *this, true );

    // number of classes

    int size = (int)_classes.size();

    vector<bool> cp( size );

    // determine crossover point (randomly)

    for( int i = _numberOfCrossoverPoints; i > 0; i-- )

    {

        while( 1 )

        {

            int p = rand() % size;

            if( !cp[ p ] )

            {

                cp[ p ] = true;

                break;

            }

        }

    }

    hash_map<CourseClass*, int>::const_iterator it1 = _classes.begin();

    hash_map<CourseClass*, int>::const_iterator it2 = parent2._classes.begin();

    // make new code by combining parent codes

    bool first = rand() % 2 == 0;

    for( int i = 0; i < size; i++ )

    {

        if( first )

        {

            // insert class from first parent into new chromosome's calss table

            n->_classes.insert( pair<CourseClass*, int>( ( *it1 ).first, ( *it1 ).second ) );

            // all time-space slots of class are copied

            for( int i = ( *it1 ).first->GetDuration() - 1; i >= 0; i-- )

                n->_slots[ ( *it1 ).second + i ].push_back( ( *it1 ).first );

        }

        else

        {

            // insert class from second parent into new chromosome's calss table

            n->_classes.insert( pair<CourseClass*, int>( ( *it2 ).first, ( *it2 ).second ) );

            // all time-space slots of class are copied

            for( int i = ( *it2 ).first->GetDuration() - 1; i >= 0; i-- )

                n->_slots[ ( *it2 ).second + i ].push_back( ( *it2 ).first );

        }

        // crossover point

        if( cp[ i ] )

            // change soruce chromosome

            first = !first;

        it1++;

        it2++;

    }

    n->CalculateFitness();

    // return smart pointer to offspring

    return n;

}

// Performs mutation on chromosome

void Schedule::Mutation()

{

    // check probability of mutation operation

    if( rand() % 100 > _mutationProbability )

        return;

    // number of classes

    int numberOfClasses = (int)_classes.size();

    // number of time-space slots

    int size = (int)_slots.size();

    // move selected number of classes at random position

    for( int i = _mutationSize; i > 0; i-- )

    {

        // select ranom chromosome for movement

        int mpos = rand() % numberOfClasses;

        int pos1 = 0;

        hash_map<CourseClass*, int>::iterator it = _classes.begin();

        for( ; mpos > 0; it++, mpos-- )

            ;

        // current time-space slot used by class

        pos1 = ( *it ).second;

        CourseClass* cc1 = ( *it ).first;

        // determine position of class randomly

        int nr = Configuration::GetInstance().GetNumberOfRooms();

        int dur = cc1->GetDuration();

        int day = rand() % DAYS_NUM;

        int room = rand() % nr;

        int time = rand() % ( DAY_HOURS + 1 - dur );

        int pos2 = day * nr * DAY_HOURS + room * DAY_HOURS + time;

        // move all time-space slots

        for( int i = dur - 1; i >= 0; i-- )

        {

            // remove class hour from current time-space slot

            list<CourseClass*>& cl = _slots[ pos1 + i ];

            for( list<CourseClass*>::iterator it = cl.begin(); it != cl.end(); it++ )

            {

                if( *it == cc1 )

                {

                    cl.erase( it );

                    break;

                }

            }

            // move class hour to new time-space slot

            _slots.at( pos2 + i ).push_back( cc1 );

        }

        // change entry of class table to point to new time-space slots

        _classes[ cc1 ] = pos2;

    }

    CalculateFitness();

}

// Calculates fitness value of chromosome

void Schedule::CalculateFitness()

{

    // chromosome's score

    int score = 0;

    int numberOfRooms = Configuration::GetInstance().GetNumberOfRooms();

    int daySize = DAY_HOURS * numberOfRooms;

    int ci = 0;

    // check criterias and calculate scores for each class in schedule

    for( hash_map<CourseClass*, int>::const_iterator it = _classes.begin(); it != _classes.end(); ++it, ci += 5 )

    {

        // coordinate of time-space slot

        int p = ( *it ).second;

        int day = p / daySize;

        int time = p % daySize;

        int room = time / DAY_HOURS;

        time = time % DAY_HOURS;

        int dur = ( *it ).first->GetDuration();

        // check for room overlapping of classes

        bool ro = false;

        for( int i = dur - 1; i >= 0; i-- )

        {

            if( _slots[ p + i ].size() > 1 )

            {

                ro = true;

                break;

            }

        }

        // on room overlaping

        if( !ro )

            score++;

        _criteria[ ci + 0 ] = !ro;

        CourseClass* cc = ( *it ).first;

        Room* r = Configuration::GetInstance().GetRoomById( room );

        // does current room have enough seats

        _criteria[ ci + 1 ] = r->GetNumberOfSeats() >= cc->GetNumberOfSeats();

        if( _criteria[ ci + 1 ] )

            score++;

        // does current room have computers if they are required

        _criteria[ ci + 2 ] = !cc->IsLabRequired() || ( cc->IsLabRequired() && r->IsLab() );

        if( _criteria[ ci + 2 ] )

            score++;

        bool po = false, go = false;

        // check overlapping of classes for professors and student groups

        for( int i = numberOfRooms, t = day * daySize + time; i > 0; i--, t += DAY_HOURS )

        {

            // for each hour of class

            for( int i = dur - 1; i >= 0; i-- )

            {

                // check for overlapping with other classes at same time

                const list<CourseClass*>& cl = _slots[ t + i ];

                for( list<CourseClass*>::const_iterator it = cl.begin(); it != cl.end(); it++ )

                {

                    if( cc != *it )

                    {

                        // professor overlaps?

                        if( !po && cc->ProfessorOverlaps( **it ) )

                            po = true;

                        // student group overlaps?

                        if( !go && cc->GroupsOverlap( **it ) )

                            go = true;

                        // both type of overlapping? no need to check more

                        if( po && go )

                            goto total_overlap;

                    }

                }

            }

        }

total_overlap:

        // professors have no overlaping classes?

        if( !po )

            score++;

        _criteria[ ci + 3 ] = !po;

        // student groups has no overlaping classes?

        if( !go )

            score++;

        _criteria[ ci + 4 ] = !go;

    }

    // calculate fitess value based on score

    _fitness = (float)score / ( Configuration::GetInstance().GetNumberOfCourseClasses() * DAYS_NUM );

}

// Pointer to global instance of algorithm

Algorithm* Algorithm::_instance = NULL;

// Synchronization of creation and destruction of global instance

CCriticalSection Algorithm::_instanceSect;

// Returns reference to global instance of algorithm

Algorithm& Algorithm::GetInstance()

{

    CSingleLock lock( &_instanceSect, TRUE );

    // global instance doesn't exist?

    if( _instance == NULL )

    {

        // set seed for random generator

        srand( GetTickCount() );

        // make prototype of chromosomes

        Schedule* prototype = new Schedule( 2, 2, 80, 3 );

        // make new global instance of algorithm using chromosome prototype

        _instance = new Algorithm( 100, 8, 5, prototype, new ScheduleObserver() );

    }

    return *_instance;

}

// Frees memory used by gloval instance

void Algorithm::FreeInstance()

{

    CSingleLock lock( &_instanceSect, TRUE );

    // free memory used by global instance if it exists

    if( _instance != NULL )

    {

        delete _instance->_prototype;

        delete _instance->_observer;

        delete _instance;

        _instance = NULL;

    }

}

// Initializes genetic algorithm

Algorithm::Algorithm(int numberOfChromosomes, int replaceByGeneration, int trackBest,

                    Schedule* prototype, ScheduleObserver* observer) : _replaceByGeneration(replaceByGeneration),

                    _currentBestSize(0),

                    _prototype(prototype),

                    _observer(observer),

                    _currentGeneration(0),

                    _state(AS_USER_STOPED)

{

    // there should be at least 2 chromosomes in population

    if( numberOfChromosomes < 2 )

        numberOfChromosomes = 2;

    // and algorithm should track at least on of best chromosomes

    if( trackBest < 1 )

        trackBest = 1;

    if( _replaceByGeneration < 1 )

        _replaceByGeneration = 1;

    else if( _replaceByGeneration > numberOfChromosomes - trackBest )

        _replaceByGeneration = numberOfChromosomes - trackBest;

    // reserve space for population

    _chromosomes.resize( numberOfChromosomes );

    _bestFlags.resize( numberOfChromosomes );

    // reserve space for best chromosome group

    _bestChromosomes.resize( trackBest );

    // clear population

    for( int i = (int)_chromosomes.size() - 1; i >= 0; --i )

    {

        _chromosomes[ i ] = NULL;

        _bestFlags[ i ] = false;

    }

}

// Frees used resources

Algorithm::~Algorithm()

{

    // clear population by deleting chromosomes

    for( vector<Schedule*>::iterator it = _chromosomes.begin(); it != _chromosomes.end(); ++it )

    {

        if( *it )

            delete *it;

    }

}

void Algorithm::Start()

{

    if( !_prototype )

        return;

    CSingleLock lock( &_stateSect, TRUE );

    // do not run already running algorithm

    if( _state == AS_RUNNING )

        return;

    _state = AS_RUNNING;

    lock.Unlock();

    if( _observer )

        // notify observer that execution of algorithm has changed it state

        _observer->EvolutionStateChanged( _state );

    // clear best chromosome group from previous execution

    ClearBest();

    // initialize new population with chromosomes randomly built using prototype

    int i = 0;

    for( vector<Schedule*>::iterator it = _chromosomes.begin(); it != _chromosomes.end(); ++it, ++i )

    {

        // remove chromosome from previous execution

        if( *it )

            delete *it;

        // add new chromosome to population

        *it = _prototype->MakeNewFromPrototype();

        AddToBest( i );

    }

    _currentGeneration = 0;

    while( 1 )

    {

        lock.Lock();

        // user has stopped execution?

        if( _state != AS_RUNNING )

        {

            lock.Unlock();

            break;

        }

        Schedule* best = GetBestChromosome();

        // algorithm has reached criteria?

        if( best->GetFitness() >= 1 )

        {

            _state = AS_CRITERIA_STOPPED;

            lock.Unlock();

            break;

        }

        lock.Unlock();

        // produce offepsing

        vector<Schedule*> offspring;

        offspring.resize( _replaceByGeneration );

        for( int j = 0; j < _replaceByGeneration; j++ )

        {

            // selects parent randomly

            Schedule* p1 = _chromosomes[ rand() % _chromosomes.size() ];

            Schedule* p2 = _chromosomes[ rand() % _chromosomes.size() ];

            offspring[ j ] = p1->Crossover( *p2 );

            offspring[ j ]->Mutation();

        }

        // replace chromosomes of current operation with offspring

        for( int j = 0; j < _replaceByGeneration; j++ )

        {

            int ci;

            do

            {

                // select chromosome for replacement randomly

                ci = rand() % (int)_chromosomes.size();

                // protect best chromosomes from replacement

            } while( IsInBest( ci ) );

            // replace chromosomes

            delete _chromosomes[ ci ];

            _chromosomes[ ci ] = offspring[ j ];

            // try to add new chromosomes in best chromosome group

            AddToBest( ci );

        }

        // algorithm has found new best chromosome

        if( best != GetBestChromosome() && _observer )

            // notify observer

            _observer->NewBestChromosome( *GetBestChromosome() );

        _currentGeneration++;

    }

    if( _observer )

        // notify observer that execution of algorithm has changed it state

        _observer->EvolutionStateChanged( _state );

}

// Stops execution of algorithm

void Algorithm::Stop()

{

    CSingleLock lock( &_stateSect, TRUE );

    if( _state == AS_RUNNING )

        _state = AS_USER_STOPED;

    lock.Unlock();

}

// Returns pointer to best chromosomes in population

Schedule* Algorithm::GetBestChromosome() const

{

    return _chromosomes[ _bestChromosomes[ 0 ] ];

}

// Tries to add chromosomes in best chromosome group

void Algorithm::AddToBest(int chromosomeIndex)

{

    // don't add if new chromosome hasn't fitness big enough for best chromosome group

    // or it is already in the group?

    if( ( _currentBestSize == (int)_bestChromosomes.size() &&

        _chromosomes[ _bestChromosomes[ _currentBestSize - 1 ] ]->GetFitness() >=

        _chromosomes[ chromosomeIndex ]->GetFitness() ) || _bestFlags[ chromosomeIndex ] )

        return;

    // find place for new chromosome

    int i = _currentBestSize;

    for( ; i > 0; i-- )

    {

        // group is not full?

        if( i < (int)_bestChromosomes.size() )

        {

            // position of new chromosomes is found?

            if( _chromosomes[ _bestChromosomes[ i - 1 ] ]->GetFitness() >

                _chromosomes[ chromosomeIndex ]->GetFitness() )

                break;

            // move chromosomes to make room for new

            _bestChromosomes[ i ] = _bestChromosomes[ i - 1 ];

        }

        else

            // group is full remove worst chromosomes in the group

            _bestFlags[ _bestChromosomes[ i - 1 ] ] = false;

    }

    // store chromosome in best chromosome group

    _bestChromosomes[ i ] = chromosomeIndex;

    _bestFlags[ chromosomeIndex ] = true;

    // increase current size if it has not reached the limit yet

    if( _currentBestSize < (int)_bestChromosomes.size() )

        _currentBestSize++;

}

// Returns TRUE if chromosome belongs to best chromosome group

bool Algorithm::IsInBest(int chromosomeIndex)

{

    return _bestFlags[ chromosomeIndex ];

}

// Clears best chromosome group

void Algorithm::ClearBest()

{

    for( int i = (int)_bestFlags.size() - 1; i >= 0; --i )

        _bestFlags[ i ] = false;

    _currentBestSize = 0;

}

void Algorithm::TabuSearch(int chromosomeIndex)

{

// don't add if new chromosome hasn't fitness big enough for best chromosome group

    // or it is already in the group?

    if( ( _currentBestSize == (int)_bestChromosomes.size() &&

        _chromosomes[ _bestChromosomes[ _currentBestSize - 1 ] ]->GetFitness() >=

        _chromosomes[ chromosomeIndex ]->GetFitness() ) || _bestFlags[ chromosomeIndex ] )

        return;

    // find place for new chromosome

    int i = _currentBestSize;

    for( ; i > 0; i-- )

    {

        // group is not full?

        if( i < (int)_bestChromosomes.size() )

        {

            // position of new chromosomes is found?

            if( _chromosomes[ _bestChromosomes[ i - 1 ] ]->GetFitness() >

                _chromosomes[ chromosomeIndex ]->GetFitness() )

                break;

            // move chromosomes to make room for new

            _bestChromosomes[ i ] = _bestChromosomes[ i - 1 ];

        }

        else

            // group is full remove worst chromosomes in the group

            _bestFlags[ _bestChromosomes[ i - 1 ] ] = false;

    }

    // store chromosome in best chromosome group

    _bestChromosomes[ i ] = chromosomeIndex;

    _bestFlags[ chromosomeIndex ] = true;

    // increase current size if it has not reached the limit yet

    if( _currentBestSize < (int)_bestChromosomes.size() )

        _currentBestSize++;

}

// Returns TRUE if chromosome belongs to best chromosome group

bool Algorithm::TabuIsInBest(int chromosomeIndex)

{

    return _bestFlags[ chromosomeIndex ];

}


// Clears best chromosome group

/*

void Algorithm::TabuClearBest()

{

    for( int i = (int)_bestFlags.size() - 1; i >= 0; --i )

        _bestFlags[ i ] = false;

    _currentBestSize = 0;

}

*/





