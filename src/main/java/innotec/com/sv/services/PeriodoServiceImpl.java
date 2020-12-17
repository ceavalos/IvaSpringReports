package innotec.com.sv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import innotec.com.sv.dao.IperiodoDao;
import innotec.com.sv.entities.Periodo;


@Service
public class PeriodoServiceImpl implements PeriodoService {

	@Autowired
	IperiodoDao iperiodoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Periodo> findAll() {
		// TODO Auto-generated method stub
		return (List<Periodo>) iperiodoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Periodo> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return iperiodoDao.findAll(pageable)  ;
	}

	@Override
	@Transactional(readOnly = true)
	public Periodo findById(Long id) {
		// TODO Auto-generated method stub
		return iperiodoDao.findById(id).orElse(null)  ;
	}

	@Override
	@Transactional
	public Periodo save(Periodo periodo) {
		// TODO Auto-generated method stub
		return iperiodoDao.save(periodo)  ;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		iperiodoDao.deleteById(id);
		
	}

}
