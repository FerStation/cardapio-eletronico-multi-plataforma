package BancoDeDados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Interface.CategoriaSubCategoria;
import PacoteLogico.CriptografarMD5;
import Tabelas.Categoria;
import Tabelas.SubCategoria;
import Tabelas.TMCaixa;
import Tabelas.TMPedido;
import Tabelas.TMProdutos;
import Tabelas.TMUsuarios;
import Trasferencia.DtoCaixa;
import Trasferencia.DtoCategoriaSubCategoria;
import Trasferencia.DtoProdutos;
import Trasferencia.DtoUsuario;
import com.mysql.jdbc.Connection;

public class Dao {

	private Connection conexao;
	
	CriptografarMD5 md5 = new CriptografarMD5();
	
	public Dao(){
		
		try {
			
			//efetua a conexao com o banco de dados
			this.conexao = (Connection) CriaConexao.getConexao();
			
		} catch (SQLException e) {
		}
	}
	
	public void insereUsuario(DtoUsuario usuario){
		
		String query = "INSERT INTO usuario (login, nv_acesso, usuario_ativo, nome, senha, email, rg, cpf, telefone, celular) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(query);
			
			try {
				
				stmt.setString(1, usuario.getLogin());
				stmt.setString(2, usuario.getNvAcesso());
				stmt.setString(3, usuario.getUsuarioAtivo());
				stmt.setString(4, usuario.getNome());
				stmt.setString(5, md5.criptografarMD5(usuario.getSenha()));
				stmt.setString(6, usuario.getEmail());
				stmt.setString(7, usuario.getRg());
				stmt.setString(8, usuario.getCpf());
				stmt.setString(9, usuario.getTelefone());
				stmt.setString(10, usuario.getCelular());
				
				stmt.execute();
				 
				JOptionPane.showMessageDialog(null,"Usuario: "+usuario.getNome()+" Cadastrado com sucesso!\nSenha provisória:"+usuario.getSenha(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insereProduto(DtoProdutos produto){
		
		//cria a variavel que ira receber a query
				String sql = "SELECT ID FROM sub_categoria WHERE nome=?";
				
				try {
					//prepara a conexao
					PreparedStatement stmt = conexao.prepareStatement(sql);
					
					try {
						stmt.setString(1, produto.getSubCategoria());
						//Executa a query de leitura atraves do ResultSet
						ResultSet rs = stmt.executeQuery();
						
						//Executa a leitura
						while(rs.next()){
							
							//salva os dados em uma ArrayList
							produto.setIdSubCategoria(rs.getInt("ID"));

						}
						
						rs.close(); //termina a leitura
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					stmt.close(); //fecha a conexão com o banco de dados
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		
		String query = "INSERT INTO produtos (ID_sub_categoria, imagem_produto, disponivel, exibir, nome_produto, ingredientes, tempo_preparo, preco) VALUES(?,?,?,?,?,?,?,?)";
		
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(query);
			
			try {
				
				stmt.setInt(1, produto.getIdSubCategoria());
				stmt.setString(2, produto.getImagem());
				stmt.setString(3, produto.getDisponivel());
				stmt.setString(4, produto.getExibir());
				stmt.setString(5, produto.getNomeProduto());
				stmt.setString(6, produto.getIngredientes());
				stmt.setInt(7, produto.getTempoPreparo());
				stmt.setDouble(8, produto.getPreco());
				
				stmt.execute();
				 
				JOptionPane.showMessageDialog(null,"Produto: "+produto.getNomeProduto()+" Cadastrada com sucesso!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insereCategoria(DtoCategoriaSubCategoria categoria){
		
		String query = "INSERT INTO categoria (imagem_categoria, descricao_categoria, nome) VALUES (?,?,?)";
		
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(query);
			
			try {
				
				stmt.setString(1, categoria.getImagem());
				stmt.setString(2, categoria.getDescricao() );
				stmt.setString(3, categoria.getNome() );
				
				stmt.execute();
				 
				JOptionPane.showMessageDialog(null,"Categoria: "+categoria.getNome()+" Cadastrada com sucesso!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterarCategoria(DtoCategoriaSubCategoria categoria){
		
		String query = "UPDATE categoria SET imagem_categoria=?, descricao_categoria=?, nome=? WHERE ID=?";
		
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(query);
			
			try {
				
				stmt.setString(1, categoria.getImagem());
				stmt.setString(2, categoria.getDescricao() );
				stmt.setString(3, categoria.getNome());
				stmt.setInt(4, categoria.getIdcategoria());
				
				stmt.execute();
				 
				JOptionPane.showMessageDialog(null,"Categoria: "+categoria.getNome()+" Alterada com sucesso!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void alterarSubCategoria(DtoCategoriaSubCategoria subcategoria){
		
		//cria a variavel que ira receber a query
		String sql = "SELECT ID FROM categoria WHERE nome=?";
		
		try {
			//prepara a conexao
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			try {
				stmt.setString(1, subcategoria.getCategoria());
				//Executa a query de leitura atraves do ResultSet
				ResultSet rs = stmt.executeQuery();
				
				//Executa a leitura
				while(rs.next()){
					
					//salva os dados em uma ArrayList
					subcategoria.setIdcategoria(rs.getInt("ID"));

				}
				
				rs.close(); //termina a leitura
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			stmt.close(); //fecha a conexão com o banco de dados
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		String query = "UPDATE sub_categoria SET id_categoria=?, imagem_sub_categoria=?, descricao_sub_categoria=?, nome=? WHERE ID=?";
		
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(query);
			
			try {
				
				stmt.setInt(1, subcategoria.getIdcategoria());
				stmt.setString(2, subcategoria.getImagem());
				stmt.setString(3, subcategoria.getDescricao() );
				stmt.setString(4, subcategoria.getNome());
				stmt.setInt(5, subcategoria.getIdSubCategoria());
				
				stmt.execute();
				 
				JOptionPane.showMessageDialog(null,"Sub-Categoria: "+subcategoria.getNome()+" Alterada com sucesso!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterarProduto(DtoProdutos produto){
		
		//cria a variavel que ira receber a query
		String sql = "SELECT categoria.ID AS categoria_id, sub_categoria.ID AS sub_categoria_id FROM categoria, sub_categoria WHERE categoria.nome=? AND sub_categoria.nome=?";
		
		try {
			//prepara a conexao
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			try {
				stmt.setString(1, produto.getCategoria());
				stmt.setString(2, produto.getSubCategoria());
				//Executa a query de leitura atraves do ResultSet
				ResultSet rs = stmt.executeQuery();
				
				//Executa a leitura
				while(rs.next()){
					
					//salva os dados em uma ArrayList
					produto.setIdSubCategoria(rs.getInt("sub_categoria_id"));

				}
				
				rs.close(); //termina a leitura
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			stmt.close(); //fecha a conexão com o banco de dados
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		String query = "UPDATE produtos SET ID_sub_categoria=?, imagem_produto=?, disponivel=?, exibir=?, nome_produto=?, ingredientes=?, tempo_preparo=?, preco=? WHERE ID=?";
		
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(query);
			
			try {
				
				stmt.setInt(1, produto.getIdSubCategoria());
				stmt.setString(2, produto.getImagem());
				stmt.setString(3, produto.getDisponivel());
				stmt.setString(4, produto.getExibir());
				stmt.setString(5, produto.getNomeProduto());
				stmt.setString(6, produto.getIngredientes());
				stmt.setInt(7, produto.getTempoPreparo());
				stmt.setDouble(8, produto.getPreco());
				stmt.setInt(9, produto.getId());
				
				stmt.execute();
				 
				JOptionPane.showMessageDialog(null,"Produto: "+produto.getNomeProduto()+" Alterado com sucesso!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterarUsuario(DtoUsuario usuario){
		
		String query = "UPDATE usuario SET nv_acesso=?, usuario_ativo=?, nome=?, email=?, rg=?, cpf=?, telefone=?, celular=? WHERE login=?";
		
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(query);
			
			try {
				
				stmt.setString(1, usuario.getNvAcesso());
				stmt.setString(2, usuario.getUsuarioAtivo());
				stmt.setString(3, usuario.getNome());
				stmt.setString(4, usuario.getEmail());
				stmt.setString(5, usuario.getRg());
				stmt.setString(6, usuario.getCpf());
				stmt.setString(7, usuario.getTelefone());
				stmt.setString(8, usuario.getCelular());
				stmt.setString(9, usuario.getLogin());
				
				stmt.execute();
				 
				JOptionPane.showMessageDialog(null,"Usuario Alterado com sucesso!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insereSubCategoria(DtoCategoriaSubCategoria subCategoria){
		
		//cria a variavel que ira receber a query
				String sql = "SELECT ID FROM categoria WHERE nome=?";
				
				try {
					//prepara a conexao
					PreparedStatement stmt = conexao.prepareStatement(sql);
					
					try {
						stmt.setString(1, subCategoria.getCategoria());
						//Executa a query de leitura atraves do ResultSet
						ResultSet rs = stmt.executeQuery();
						
						//Executa a leitura
						while(rs.next()){
							
							//salva os dados em uma ArrayList
							subCategoria.setIdcategoria(rs.getInt("ID"));

						}
						
						rs.close(); //termina a leitura
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					stmt.close(); //fecha a conexão com o banco de dados
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		
		String query = "INSERT INTO sub_categoria (id_categoria, imagem_sub_categoria, descricao_sub_categoria, nome) VALUES (?,?,?,?)";
		
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(query);
			
			try {
				
				stmt.setInt(1, subCategoria.getIdcategoria());
				stmt.setString(2, subCategoria.getImagem());
				stmt.setString(3, subCategoria.getDescricao());
				stmt.setString(4, subCategoria.getNome());
				
				stmt.execute();
				 
				JOptionPane.showMessageDialog(null,"Sub-Categoria: "+subCategoria.getNome()+" Cadastrada com sucesso!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Categoria> listaCategoriaTabela(){
		
		//Cria a ArrayList
		List<Categoria> categoria = new ArrayList<Categoria>();
		
		//cria a variavel que ira receber a query
		String sql = "SELECT * FROM categoria";
		
		try {
			//prepara a conexao
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			try {
				//Executa a query de leitura atraves do ResultSet
				ResultSet rs = stmt.executeQuery();
				
				//Executa a leitura
				while(rs.next()){
					
					//salva os dados em uma ArrayList
					categoria.add(new Categoria(rs.getInt("ID"), rs.getString("nome"), rs.getString("imagem_categoria"), rs.getString("descricao_categoria")));

				}
				
				rs.close(); //termina a leitura
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			stmt.close(); //fecha a conexão com o banco de dados
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		return categoria;

	}
	
	public List<TMProdutos> listaProdutosTabela(){
		
		//Cria a ArrayList
		List<TMProdutos> produtos = new ArrayList<TMProdutos>();
		
		//cria a variavel que ira receber a query
		String sql = "SELECT produtos.ID, sub_categoria.ID_categoria, produtos.ID_sub_categoria, categoria.nome AS \"categoria\", sub_categoria.nome AS \"sub_categoria\", produtos.imagem_produto, produtos.disponivel, produtos.exibir, produtos.nome_produto, produtos.ingredientes, produtos.tempo_preparo, produtos.preco FROM produtos, sub_categoria, categoria WHERE produtos.ID_sub_categoria = sub_categoria.ID AND sub_categoria.ID_categoria = categoria.ID";
		
		try {
			//prepara a conexao
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			try {
				//Executa a query de leitura atraves do ResultSet
				ResultSet rs = stmt.executeQuery();
				
				//Executa a leitura
				while(rs.next()){
					
					//salva os dados em uma ArrayList
					produtos.add(new TMProdutos(rs.getInt("ID"), rs.getString("disponivel"), rs.getString("exibir") , rs.getString("nome_produto"), rs.getString("ingredientes"), rs.getInt("tempo_preparo"), rs.getDouble("preco"), rs.getString("imagem_produto"), rs.getString("categoria"), rs.getString("sub_categoria")));
				}
				
				rs.close(); //termina a leitura
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			stmt.close(); //fecha a conexão com o banco de dados
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		return produtos;

	}
	
	public List<TMUsuarios> listaUsuariosTabela(){
		
		//Cria a ArrayList
		List<TMUsuarios> usuarios = new ArrayList<TMUsuarios>();
				
			//cria a variavel que ira receber a query
			String sql = "SELECT * FROM usuario";
				
				try {
					//prepara a conexao
					PreparedStatement stmt = conexao.prepareStatement(sql);
					
					try {
						//Executa a query de leitura atraves do ResultSet
						ResultSet rs = stmt.executeQuery();
						
						//Executa a leitura
						while(rs.next()){
							
							//salva os dados em uma ArrayList
							usuarios.add(new TMUsuarios(rs.getString("login"), rs.getString("nv_acesso"), rs.getString("nome"), rs.getString("email"), rs.getString("rg"), rs.getString("cpf"), rs.getString("telefone"), rs.getString("celular")));

						}
						
						rs.close(); //termina a leitura
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					stmt.close(); //fecha a conexão com o banco de dados
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			
				return usuarios;
	}
	
	public List<SubCategoria> listaSubCategoriaTabela(){
		
		//Cria a ArrayList
				List<SubCategoria> subCategoria = new ArrayList<SubCategoria>();
				
				//cria a variavel que ira receber a query
				String sql = "SELECT sub_categoria.ID, categoria.nome AS nome_categoria, sub_categoria.imagem_sub_categoria, sub_categoria.descricao_sub_categoria, sub_categoria.nome FROM sub_categoria INNER JOIN categoria ON sub_categoria.ID_categoria = categoria.ID ORDER BY categoria.nome ASC";
				
				try {
					//prepara a conexao
					PreparedStatement stmt = conexao.prepareStatement(sql);
					
					try {
						//Executa a query de leitura atraves do ResultSet
						ResultSet rs = stmt.executeQuery();
						
						//Executa a leitura
						while(rs.next()){
							
							//salva os dados em uma ArrayList
							subCategoria.add(new SubCategoria(rs.getInt("ID"), rs.getString("imagem_sub_categoria"), rs.getString("descricao_sub_categoria"), rs.getString("nome_Categoria"), rs.getString("nome")));

						}
						
						rs.close(); //termina a leitura
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					stmt.close(); //fecha a conexão com o banco de dados
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			
				return subCategoria;
	}
	
	public void deletaCategoria(DtoCategoriaSubCategoria categoria){
		
		String sql = "DELETE FROM categoria WHERE ID=?";
		
		try {
			//prepara a conexao
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			try {
				stmt.setInt(1, categoria.getIdcategoria());
				
				stmt.execute();
				
				JOptionPane.showMessageDialog(null, "Categoria excluida com sucesso");
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			stmt.close(); //fecha a conexão com o banco de dados
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void deletaProdutos(DtoProdutos produtos){
		
		String sql = "DELETE FROM produtos WHERE ID=?";
		
		try {
			//prepara a conexao
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			try {
				stmt.setInt(1, produtos.getId());
				
				stmt.execute();
				
				JOptionPane.showMessageDialog(null, "Categoria excluida com sucesso");
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			stmt.close(); //fecha a conexão com o banco de dados
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void deletaSubCategoria(DtoCategoriaSubCategoria subCategoria){
		
		String sql = "DELETE FROM sub_categoria WHERE ID=?";
		
		try {
			//prepara a conexao
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			try {
				stmt.setInt(1, subCategoria.getIdSubCategoria());
				
				stmt.execute();
				
				JOptionPane.showMessageDialog(null, "Sub Categoria excluida com sucesso");
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			stmt.close(); //fecha a conexão com o banco de dados
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void deletaUsuario(DtoUsuario usuario){
		
		String sql = "DELETE FROM usuario WHERE login=?";
		
		try {
			//prepara a conexao
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			try {
				stmt.setString(1, usuario.getLogin());
				
				stmt.execute();
				
				JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso");
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			stmt.close(); //fecha a conexão com o banco de dados
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Esse metodo alimenta a(s) JComboBox Categoria
	 * 
	 * @return uma ArrayList com as categorias
	 */
	public ArrayList<String> listaCategorias(){
		
		//Cria a ArrayList
		ArrayList<String> categoria = new ArrayList<String>();
		
		//cria a variavel que ira receber a query
		String sql = "SELECT nome FROM categoria";
		
		try {
			//prepara a conexao
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			try {
				//Executa a query de leitura atraves do ResultSet
				ResultSet rs = stmt.executeQuery();
				
				//Executa a leitura
				while(rs.next()){
					
					//salva os dados em uma ArrayList
					categoria.add(rs.getString("nome"));

				}
				
				rs.close(); //termina a leitura
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			stmt.close(); //fecha a conexão com o banco de dados
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		return categoria;

	}
	
	public boolean validaLogin(String login, String senha){
		
		boolean loginValido = false;
		
		String loginBd = null;
		String SenhaBd = null;
		
		String query = "SELECT login, senha FROM usuario WHERE login=? AND senha=?";
		
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(query);
			
			try {
				
				stmt = conexao.prepareStatement(query);
				
				stmt.setString(1, login);
				stmt.setString(2, senha);
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					
					loginBd = rs.getString("login");
					SenhaBd = rs.getString("senha");
				}
				
				rs.close();
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(login.equals(loginBd) && senha.equals(SenhaBd)){
			
			loginValido = true;
		}
		
		return loginValido;
	}
	
public ArrayList<String> listaSubCategoria(String categoria){
		
		//Cria a ArrayList
		ArrayList<String> subCategoria = new ArrayList<>();
		
		//Cria a String que ira receber a query
		String sql = "SELECT sub_categoria.ID, categoria.nome AS categoria, sub_categoria.Nome AS sub_categoria FROM sub_categoria INNER JOIN categoria WHERE sub_categoria.ID_categoria = categoria.ID AND categoria.nome =?";
		
		try {
			//prepara a conexao
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			try {
				
				stmt.setString(1, categoria);
				
				//Executa a query de leitura atraves do ResultSet
				ResultSet rs = stmt.executeQuery();
				
				
				//Executa a leitura
				while(rs.next()){
					
					//salva os dados em uma ArrayList
					subCategoria.add(rs.getString("sub_categoria"));

				}
				
				rs.close(); //termina a leitura
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			stmt.close(); //fecha a conexão com o banco de dados
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return subCategoria;
		
	}

	public List<TMCaixa> selectListaProdutos(int mesa){
		
		//Cria a String que ira receber a query
				String sql = "SELECT lista_de_produtos.ID, lista_de_produtos.ID_pedido, pedido.ID_mesa, lista_de_produtos.quantidade, produtos.nome_produto, produtos.preco FROM lista_de_produtos, produtos, pedido WHERE pedido.ID_mesa=? AND pedido.pedido_pronto='N' AND lista_de_produtos.ID_produto = produtos.ID AND pedido.ID = lista_de_produtos.ID_pedido";
				
				final java.util.List<TMCaixa> lista = new java.util.ArrayList<>();
				double total = 0;
				DtoCaixa caixa = new DtoCaixa();
 				
				try {
					//prepara a conexao
					PreparedStatement stmt = conexao.prepareStatement(sql);
					
					try {
						
						stmt.setInt(1, mesa);
						
						//Executa a query de leitura atraves do ResultSet
						ResultSet rs = stmt.executeQuery();
						
						
						//Executa a leitura
						while(rs.next()){
							
							lista.add(new TMCaixa(rs.getInt("ID"), rs.getInt("ID_mesa"), rs.getInt("quantidade"), rs.getString("nome_produto"), rs.getDouble("preco")));
							total += rs.getDouble("preco");
							caixa.setIdPedido(rs.getInt("ID_pedido"));
						}
						
						rs.close(); //termina a leitura
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					stmt.close(); //fecha a conexão com o banco de dados
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				caixa.setTotal(total);
				return lista;
	}
	
	public void finalizaConta(int idPedido){
		
		String query = "UPDATE pedido SET pedido_pronto='Y' WHERE ID=?";
		
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(query);
			
			try {
				
				stmt.setInt(1, idPedido);
				
				stmt.execute();
				 
				JOptionPane.showMessageDialog(null,"Pedido Finalizado");
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<TMPedido> selectPedido(){
		
		//Cria a String que ira receber a query
		String sql = "SELECT lista_de_produtos.ID, pedido.ID_mesa, lista_de_produtos.quantidade, produtos.nome_produto FROM lista_de_produtos, pedido, produtos WHERE lista_de_produtos.ID_pedido = pedido.ID AND lista_de_produtos.ID_produto = produtos.ID AND lista_de_produtos.pedido_efetuado='Y' AND lista_de_produtos.pedido_pronto='N'";
		
		final java.util.List<TMPedido> lista = new java.util.ArrayList<>();
			
		try {
			//prepara a conexao
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			try {
							
				//Executa a query de leitura atraves do ResultSet
				ResultSet rs = stmt.executeQuery();
				
				
				//Executa a leitura
				while(rs.next()){
					
					lista.add(new TMPedido(rs.getInt("ID"), rs.getInt("ID_mesa"), rs.getInt("quantidade"), rs.getString("nome_produto")));
				}
				
				rs.close(); //termina a leitura
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			stmt.close(); //fecha a conexão com o banco de dados
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public void finalizaPedido(int idPedido){
		
		String query = "UPDATE lista_de_produtos SET pedido_pronto='Y' WHERE ID=?";
		
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(query);
			
			try {
				
				stmt.setInt(1, idPedido);
				stmt.execute();
				 
				//JOptionPane.showMessageDialog(null,"Categoria: "+categoria.getNome()+" Alterada com sucesso!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
